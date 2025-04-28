package entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyRevenueInfo implements Serializable{

    private static final long serialVersionUID = -8592648180033244511L;
	private int month;
    private double totalRevenue;

    public MonthlyRevenueInfo(int month, double totalRevenue) {
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

}