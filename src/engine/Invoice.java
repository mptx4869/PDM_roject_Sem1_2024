package engine;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Gatherer.Integrator;

public class Invoice {
    private Map<Integer,Integer> selectedMap;
    private BigDecimal total_Amount;
    
    public Invoice(Map<Integer,Integer> selectedMap){
        this.selectedMap = selectedMap;
        setTotal_Amount();
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
    
    public void setSelectedMap(Map<Integer, Integer> selectedMap) {
        this.selectedMap = selectedMap;
    }
    public Map<Integer, Integer> getSelectedMap() {
        return selectedMap;
    }

}
