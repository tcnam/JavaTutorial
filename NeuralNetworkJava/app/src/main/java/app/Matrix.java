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

    public Matrix add (double scalar){
        Matrix result=new Matrix(this.rows,this.cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]+=scalar;
            }
        }
        return result;
    }

    public Matrix add(Matrix m){
        Matrix result=new Matrix(this.rows,this.cols);
        if(this.cols!=m.getCols()||this.rows!=m.getRows()){
            System.out.println("Hai ma tran khong cung kich thuoc");
            return result;
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                this.data[i][j]+=m.getData()[i][j];
            }
        }
        return result;
    }

    public void subtract (double scalar){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]-=scalar;
            }
        }
    }

    public static Matrix subtract(Matrix a, Matrix b){
        Matrix resultM=new Matrix(a.getRows(),a.getCols());
        if(a.getCols()!=b.getCols()||a.getRows()!=b.getRows()){
            System.out.println("Hai ma tran khong cung kich thuoc");
            return resultM;
        }
        for (int i=0;i<a.getRows();i++){
            for (int j=0;j<a.getCols();j++){
                a.getData()[i][j]-=b.getData()[i][j];
            }
        }
        return resultM;
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

    public Matrix negativeMatrix(){
        Matrix result=new Matrix(this.rows,this.cols);
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                result.getData()[i][j]=-this.data[i][j];
            }
        }
        return result;
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

    public Matrix multiply(double scalar){
        Matrix results=new Matrix(this.rows, this.cols);
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                data[i][j]*=scalar;
            }
        }
        return results;
    }

    public void sigmoid(){
        for (int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                this.data[i][j]=1/(1+Math.exp(-this.data[i][j]));
            }
        }
    }

    public Matrix dsigmoid(){
        Matrix result=new Matrix(this.rows, this.cols);
        for (int i=0;i<this.rows;i++){
            for (int j=0;j<this.cols;j++){
                result.getData()[i][j]=this.data[i][j]*(1-this.data[i][j]);
            }
        }
        return result;
    }

    public Matrix log(){
        Matrix result=new Matrix(this.rows,this.cols);
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                result.getData()[i][j]=Math.log(this.getData()[i][j]);
            }
        }
        return result;
    }

    public Matrix crossEntropyLossFunc(Matrix actualOutput){
        Matrix cost=new Matrix(this.rows,this.cols);
        Matrix temp1=this.multiply(actualOutput.log());             //this*log(actualOutput)
        Matrix temp2=this.negativeMatrix().add(1);                  //1-this
        Matrix temp3=actualOutput.negativeMatrix().add(1).log();    //log(1-actualOutput)
        cost=temp1.add(temp2.multiply(temp3)).negativeMatrix();     // -(this*log(actualOutput)+(1-this)*(log(1-actualOutput)))
        return cost;
    }

    public static Matrix fromArray(double[] x){
        Matrix result=new Matrix(x.length,1);
        for(int i=0;i<x.length;i++){
            result.getData()[i][0]=x[i];
        }
        return result;
    }

    public static Matrix fromArray(ArrayList<Double> x){
        Matrix result=new Matrix(x.size(),1);
        for(int i=0;i<x.size();i++){
            result.getData()[i][0]=x.get(i);
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
