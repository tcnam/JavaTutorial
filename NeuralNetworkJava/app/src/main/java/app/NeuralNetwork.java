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
            if(i!=0&&i!=nodesInLayers.length-1){
                hidden[i]=new Matrix(nodesInLayers[i], 1);
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
//        System.out.println(weights.length);
        return output.toArray();
    }

    public void train(double[] x, double[] y){

        for (int i=weights.length-1;i>=0;i++){
            switch (weights.length-1-i){
                case 0:
                    Matrix actualOutput=Matrix.fromArray(this.predict(x));
                    Matrix expectedOutput=Matrix.fromArray(y);
                    Matrix cost=Matrix.subtract(actualOutput,expectedOutput);
                    Matrix gradient=actualOutput.dsigmoid();
                    gradient=gradient.multiply(cost);
                    gradient=gradient.multiply(this.lRate);
                    Matrix hidden_T=hidden[i].transpose();
                    Matrix delta=gradient.multiply(hidden_T);
                    weights[i]=weights[i].add(delta);
                    bias[i]=bias[i].add(gradient);
                    break;
                default:
                    Matrix who_T=weights[i+1].transpose();
                    Matrix hidden_cost=who_T.multiply(cost);
                    break;
            }
        }
        Matrix actualOutput=Matrix.fromArray(predict(x));
        Matrix expectedOutput=Matrix.fromArray(y);

        Matrix cost=Matrix.subtract(expectedOutput,actualOutput);
        Matrix gradient=actualOutput.dsigmoid();
        gradient=gradient.multiply(cost);
        gradient=gradient.multiply(this.lRate);



        for(int i=weights.length-1;i>=0;i--){
            Matrix gradientWeight=new Matrix(weights[i].getRows(),weights[i].getCols());
            Matrix gradientBias=new Matrix(weights[i].getRows(),1);
            gradientWeight=actualOutput.dsigmoid().multiply(this.lRate);
            gradientBias=actualOutput.dsigmoid().multiply(this.lRate);
            weights[i]=Matrix.subtract(weights[i],gradientWeight);
            bias[i]=Matrix.subtract(bias[i],gradientBias);
        }
    }

    public void fit(double[][] x, double[][] y, int epochs){
        for(int i=0;i<epochs;i++){
            int sampleN=(int)(Math.random()*x.length);
            this.train(x[sampleN],y[sampleN]);
        }
    }
}
