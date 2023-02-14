package app;

import java.util.ArrayList;


public class NeuralNetwork {
    private Matrix[] weights, bias, hidden;
    private double lRate=0.01;

    public NeuralNetwork(int[] nodesInLayers){
        weights=new Matrix[nodesInLayers.length-1];
        bias=new Matrix[nodesInLayers.length-1];
        hidden=new Matrix[nodesInLayers.length];
        
        for (int i=0;i<nodesInLayers.length-1;i++){
            weights[i]=new Matrix(nodesInLayers[i+1], nodesInLayers[i]);
            bias[i]=new Matrix(nodesInLayers[i+1], 1);
            if(i!=0){
                hidden[i]=new Matrix(nodesInLayers[i], 1);
            }
            if(i!=nodesInLayers.length-1){
                hidden[i+1]=new Matrix(nodesInLayers[i+1],1);
            }
        }
    }

    public ArrayList<Double> predict(double[] x){
        Matrix input=Matrix.fromArray(x);
        Matrix output=new Matrix(weights[weights.length-1].getRows(), 1);;
        for(int i=0;i<weights.length-1;i++){
            switch (i){
                case 0:
                    hidden[i+1]=weights[i].multiply(input);
                    hidden[i+1].add(bias[i]);
                    hidden[i+1].sigmoid();
                    break;
                default:
                    hidden[i+1]=weights[i].multiply(hidden[i]);
                    hidden[i+1].add(bias[i]);
                    hidden[i+1].sigmoid();
                    break;
            }
        }
        output=weights[weights.length-1].multiply(hidden[weights.length-1]);
        output.sigmoid();
        return output.toArray();
    }
}
