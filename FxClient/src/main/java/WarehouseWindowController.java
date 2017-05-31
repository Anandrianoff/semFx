import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import pojo.Product;
import pojo.Warehouse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Andrey on 28.05.2017.
 */
public class WarehouseWindowController implements Initializable{

    @FXML
    private Label warehouse;

    @FXML
    private FlowPane pane;

    @FXML
    private ListView<Product> listView;

    @FXML
    private TextField amount;

    @FXML
    private Label messages;
    @FXML
    private TextField movedAmount;
    @FXML
    private ListView<Warehouse> warehousesList;

    @FXML
    Button btn;

    @FXML
    private Button moveBtn;

    private ObservableList<Warehouse> warehouses;

    private ObservableList<Product> products;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moveBtn.setDisable(true);
        pane.setMaxWidth(200);
        btn.setDisable(true);
        products = FXCollections.observableArrayList();
        warehouses = FXCollections.observableArrayList();
        listView.setItems(products);
        listView.setMaxHeight(250);
        listView.setMaxWidth(200);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn.setDisable(false);
                if(warehousesList.getSelectionModel().getSelectedItem() != null)
                    moveBtn.setDisable(false);
                amount.setText(Integer.toString(listView.getSelectionModel().getSelectedItem().getAmount()));
                messages.setText("");
            }
        });
        warehousesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(listView.getSelectionModel().getSelectedItem() != null)
                    moveBtn.setDisable(false);
            }
        });
        warehousesList.setMaxSize(200, 250);
        warehousesList.setItems(warehouses);

        amount.setMaxWidth(180);
        warehouse.setMaxWidth(200);
    }

    public void move(){
        int amount;
        try {
            amount = Integer.parseInt(movedAmount.getText());
            if(amount < 1)
                throw new Exception();
            if(amount > listView.getSelectionModel().getSelectedItem().getAmount()){
                messages.setText("On this warehouse there are not enough products");
            }else {
                int currentAmount = listView.getSelectionModel().getSelectedItem().getAmount();
                if(currentAmount - amount < 1){
                    products.remove(listView.getSelectionModel().getSelectedItem());
                }else {
                    listView.getSelectionModel().getSelectedItem().setAmount(currentAmount - amount);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CloseableHttpClient httpclient = HttpClients.createDefault();
                            String params = "from="+currentWarehouse.getId()
                                    + "&to="+warehousesList.getSelectionModel().getSelectedItem().getId()+
                                    "&amount="+amount+"&prod="+listView.getSelectionModel().getSelectedItem().getId();
                            HttpGet httpGet = new HttpGet("http://localhost:8080/rest/moveProd?"+params);
                            CloseableHttpResponse response1 = httpclient.execute(httpGet);
                            movedAmount.setText("");
                        }catch (Exception e){}
                    }
                });
            }
        } catch (Exception e) {
            messages.setText("Only numbers more than 0");
        }
    }

    public void update(){
        try {
            int count = Integer.valueOf(amount.getText());
            if(count < 0)
                throw new Exception();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        CloseableHttpClient httpclient = HttpClients.createDefault();
                        String params = "warehouse="+currentWarehouse.getId()
                                + "&prod="+listView.getSelectionModel().getSelectedItem().getId()+
                                "&amount="+count;
                        HttpGet httpGet = new HttpGet("http://localhost:8080/rest/update?"+params);
                        CloseableHttpResponse response1 = httpclient.execute(httpGet);
                        BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
                        StringBuffer result = new StringBuffer();
//                        result.append("{\"warehouses\":");
                        String line = "";
                        while ((line = rd.readLine()) != null) {
                            result.append(line);
                        }
                        if(result.toString().contains("messages")){
                            messages.setText("Update successed");
                            listView.getSelectionModel().getSelectedItem().setAmount(count);
                        }
//                        result.append("}");
////                    JSONObject o = new JSONObject(result.toString().substring(1, result.length()-1));
//                        JSONObject ob = new JSONObject(result.toString());
//                        JSONArray arr = ob.getJSONArray("warehouses");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void loadProducts(){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8080/rest/warehouse/" + currentWarehouse.getId());
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            result.append("{\"products\":");
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            result.append("}");
            JSONObject ob = new JSONObject(result.toString());
            JSONArray arr = ob.getJSONArray("products");
            for (int i = 0; i < arr.length(); i++){
                Product p = new Product();
                p.setId(arr.getJSONObject(i).getLong("id"));
                p.setName(arr.getJSONObject(i).getString("name"));
                p.setCost(arr.getJSONObject(i).getInt("price"));
                JSONArray jarr = arr.getJSONObject(i).getJSONArray("productsOnWarehouses");
                p.setAmount(jarr.getJSONObject(0).getInt("amount"));
                products.add(p);
            }
            System.out.println();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Warehouse getCurrentWarehouse() {
        return currentWarehouse;
    }

    public void setCurrentWarehouse(Warehouse currentWarehouse) {
        this.currentWarehouse = currentWarehouse;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses.addAll(warehouses);
    }

    private Warehouse currentWarehouse;



    public void setLabel(String text){
        warehouse.setText(text);
    }
}
