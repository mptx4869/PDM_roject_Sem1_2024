package engine;

import java.math.BigDecimal;
import java.util.Map;
//import java.util.stream.Gatherer.Integrator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Invoice {
    private Map<Integer,Integer> selectedMap;
    private BigDecimal total_Amount;
    private BigDecimal total_Price;
    private DefaultTableModel tableModel ;
    public Invoice(Map<Integer,Integer> selectedMap){
        this.selectedMap = selectedMap;
        setUpInvoice();
    }
    public BigDecimal getTotal_Amount() {
        return total_Amount;
    }
    public void setTotal_Amount(BigDecimal total_Amount) {
        this.total_Amount = total_Amount;
    }
    public void setTotal_Amount(){
        int amount = 0;
        for(int entry : selectedMap.values()){
            amount += entry;
        }
    }
    public void setTotal_Price(){

    }

    public BigDecimal getTotal_Price() {
        return total_Price;
    }
    public void setUpInvoice(){
        this.total_Amount = new BigDecimal(0)   ;
        this.total_Price = new BigDecimal(0);
        String[] column  = {"Product Id", "Product Name", "Amount", "Price","Total Price"};
        DefaultTableModel model = new DefaultTableModel(column,0);
        for(Map.Entry<Integer,Integer> entry : selectedMap.entrySet() ){
            Product product = ProductList.getProductById(entry.getKey());
            model.addRow(new Object[]{entry.getKey(),product.getProduct_name(),entry.getValue(),product.getPrice(),product.getPrice().multiply(BigDecimal.valueOf(entry.getValue()))});
            this.total_Amount = total_Amount.add(BigDecimal.valueOf(entry.getValue()));
            this.total_Price = total_Price.add(product.getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        
        this.tableModel = model;
    }
    public void showInvoice(){
        if(this.selectedMap != null)
        for(Map.Entry<Integer,Integer> entry : selectedMap.entrySet()){
            System.out.println(ProductList.producMap.get(entry.getKey()).toString()+" amount = "+entry.getValue());
        }
    }
    public void setSelectedMap(Map<Integer, Integer> selectedMap) {
        this.selectedMap = selectedMap;
    }
    public Map<Integer, Integer> getSelectedMap() {
        return selectedMap;
    }
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
