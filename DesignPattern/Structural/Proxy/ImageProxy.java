package DesignPattern.Structural.Proxy;

import javafx.geometry.Point2D;

public class ImageProxy implements Image{

    private String name;

    private BitmapImage image;

    private Point2D location;

    public ImageProxy(String name){
        this.name=name;
    }


    @Override
    public void setLocation(Point2D point2d) {
        // TODO Auto-generated method stub
        if(this.image !=null){
            this.image.setLocation(point2d);
        }else{
            this.location=point2d;
        }
    }

    @Override
    public Point2D getLocation() {
        if(this.image!=null){
            return this.image.getLocation();
        }
        return this.location;
    }

    @Override
    public void render() {  
        if(this.image==null){
            image=new BitmapImage(name);
            if(location!=null){
                this.image.setLocation(this.location);
            }
        }
        image.render();
    }
    
}
