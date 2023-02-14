package app;
import java.lang.Math;
import java.util.ArrayList;


public class Matrix {
    private double [][]data;
    private int rows,cols;

    public Matrix(int rows, int cols){
        this.rows=rows;
        this.cols=cols;
        this.data=new double[rows][cols];
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                data[i][j]=Math.random()*2-1;
            }
        }
    }

    public int getRows(){
        return this.rows;
    }

    public int getCols(){
        return this.cols;
    }

    public double[][] getData(){
        return this.data;
    }

    public void add (double scalar){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]+=scalar;
            }
        }
    }

    public void add(Matrix m){
        if(this.cols!=m.getCols()||this.rows!=m.getRows()){
            System.out.println("Hai ma tran khong cung kich thuoc");
            return;
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                this.data[i][j]+=m.getData()[i][j];
            }
        }
    }

    public void subtract (double scalar){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]-=scalar;
            }
        }
    }

    public void subtract(Matrix m){
        if(this.cols!=m.getCols()||this.rows!=m.getRows()){
            System.out.println("Hai ma tran khong cung kich thuoc");
            return;
        }
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                this.data[i][j]-=m.getData()[i][j];
            }
        }
    }

    public Matrix transpose(){
        Matrix transposeM=new Matrix(this.cols, this.rows);
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                transposeM.getData()[j][i]=this.data[i][j];
            }
        }
        return transposeM;
    }

    public Matrix multiply(Matrix m){
        if(this.cols!=m.getRows()){
            System.out.println("Number of cols of the first matrix is not equal to the number of rows of the second matrix");
            return null;
        }
        Matrix results=new Matrix(this.rows, m.getCols());
        for (int i=0;i<results.getRows();i++){
            for(int j=0;j<results.getCols();j++){
                double temp=0;
                for(int k=0;k<this.cols;k++){
                    temp+=this.data[i][k]*m.getData()[k][j];
                }
                results.getData()[i][j]=temp;
            }
        }
        return results;
    }

    public void multiply(double scalar){
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                data[i][j]*=scalar;
            }
        }
    }

    public void sigmoid(){
        for (int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                this.data[i][j]=1/(1+Math.exp(-this.data[i][j]));
            }
        }
    }

    public void dsigmoid(){
        Matrix result=new Matrix(this.rows, this.cols);
        for (int i=0;i<this.rows;i++){
            for (int j=0;j<this.cols;j++){
                result.getData()[i][j]=this.data[i][j]*(1-this.data[i][j]);
            }
        }
    }

    public static Matrix fromArray(double[] x){
        Matrix result=new Matrix(x.length,1);
        for(int i=0;i<x.length;i++){
            result.getData()[i][0]=x[i];
        }
        return result;
    }

    public ArrayList<Double> toArray(){
        ArrayList<Double> result=new ArrayList<Double>();
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                result.add(this.data[i][j]);
            }
        }
        return result;
    }
}
