package org.sunsetcode.gameobject;

public class FuelTank
{
    private int amount;
    private final int maxAmount;
    private final int consumption;

    public FuelTank(int amount, int maxAmount, int consumption)
    {
        this.amount = amount;
        this.maxAmount = maxAmount;
        this.consumption = consumption;
    }

    public void fill(int newAmount)
    {
        amount = Math.min(newAmount, maxAmount);
    }

    public void burn()
    {
        int newAmount = amount - consumption;
        amount = Math.max(newAmount, 0);
    }

    public int getAmount() {
        return amount;
    }

    public int getConsumption() {
        return consumption;
    }
}
