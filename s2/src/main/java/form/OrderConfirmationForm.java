package form;

import model.Product;
import pojo.Confirmation;
import pojo.ProductCounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 11.05.2017.
 */
public class OrderConfirmationForm {

    private List<Confirmation> confirmations;

    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(List<Confirmation> confirmations) {
        this.confirmations = confirmations;
    }
}
