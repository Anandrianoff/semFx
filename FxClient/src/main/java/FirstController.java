import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import pojo.Warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andrey on 25.05.2017.
 */
public class FirstController {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ListView<Warehouse> listView;

    ObservableList<Warehouse> items = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        listView.setItems(items);
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpGet httpGet = new HttpGet("http://localhost:8080/rest/warehouses");
                    CloseableHttpResponse response1 = httpclient.execute(httpGet);
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
                    StringBuffer result = new StringBuffer();
                    result.append("{\"warehouses\":");
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    result.append("}");
//                    JSONObject o = new JSONObject(result.toString().substring(1, result.length()-1));
                    JSONObject ob = new JSONObject(result.toString());
                    JSONArray arr = ob.getJSONArray("warehouses");
                    for (int i = 0; i < arr.length(); i++)
                    {
                        Warehouse w = new Warehouse();
                        w.setCity(arr.getJSONObject(i).getString("city"));
                        w.setStreet(arr.getJSONObject(i).getString("address"));
                        w.setId(arr.getJSONObject(i).getLong("id"));
                        items.add(w);
                    }
                    listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getClickCount() == 2){
                                System.out.println("qweqweqweqew");
                                Warehouse s = listView.getSelectionModel().getSelectedItem();
                                System.out.println(s);

                                Stage st = new Stage();
                                FXMLLoader loader = new FXMLLoader();
                                try {
                                    Pane root = loader.load(getClass().getResource("fxml/currentWarehouse.fxml").openStream());
                                    WarehouseWindowController warehouseWindowController = (WarehouseWindowController)loader.getController();
                                    warehouseWindowController.setCurrentWarehouse(s);
                                    warehouseWindowController.loadProducts();
                                    warehouseWindowController.setLabel(s.toString());
                                    warehouseWindowController.setWarehouses(items);
                                    Scene scene = new Scene(root,500,400);
                                    st.setScene(scene);
                                    st.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
