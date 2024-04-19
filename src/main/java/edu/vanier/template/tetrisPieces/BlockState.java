package edu.vanier.template.tetrisPieces;

/**
 *
 * @author anton
 */
public class BlockState {

    private double posX;
    private double posY;
    private double speedX;
    private double speedY;
    private double accX;
    private double accY;
    
    private double posA;
    private double speedA;
    private double accA;

    public BlockState(double posX, double posY, double speedX, double speedY, double accX, double accY) {
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.accX = accX;
        this.accY = accY;
        
        this.posA = 0;
        this.speedA = 30;
        this.accA = 0;

    }

    // Getters and setters 
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getAccX() {
        return accX;
    }

    public void setAccX(double accX) {
        this.accX = accX;
    }

    public double getAccY() {
        return accY;
    }

    public void setAccY(double accY) {
        this.accY = accY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
    
    public double getPosA() {
        return posA;
    }

    public void setPosA(double posA) {
        this.posA = posA;
    }

    public double getSpeedA() {
        return speedA;
    }

    public void setSpeedA(double speedA) {
        this.speedA = speedA;
    }

    public double getAccA() {
        return accA;
    }

    public void setAccA(double accA) {
        this.accA = accA;
    }

}
