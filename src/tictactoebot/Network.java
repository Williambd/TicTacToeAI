/*
 * Created by WilliamBD
 * On 19/06/2018
 * to simulate a simple neural network of 3 layers of 9 cells.
 */


package tictactoebot;

/**
 *
 * @author widea9928
 */
public class Network {
    public double[] inputCells = new double[9];
    public double[] secondLayer = new double[9];
    public double[] outputCells = new double[9];
    
    public Individual individual;
    
    //first corresponds to second layer cells, second is weighting for each input
    public double[][] in2secWeight = new double [9][9];
    public double[][] sec2outWeight = new double [9][9];
    
    public void excecuteNetwork(){
        //for each cell in secondLayer
        for (int i=0; i<secondLayer.length; i++){
            double sum = 0;
            //for each weighting
            for (int j=0; j<9; j++){
                sum+= inputCells[j]*in2secWeight[i][j];
            }
            //each layer equals its sum with a logistic sigmoid function
            secondLayer[i] = 1/Math.pow(Math.E,-sum);
        }
        for (int i=0; i< outputCells.length; i++){
            double sum =0;
            
            //for each weighting
            for(int j=0; j<9; j++){
                sum+= secondLayer[j]*sec2outWeight[i][j];
            }
            
            outputCells[i]=1/Math.pow(Math.E,-sum);
        }
        //doesn't allow invalid moves
        for (int i=0; i<9; i++){
            if (inputCells[i]==1 || inputCells[i]==-1){
                outputCells[i] = 0;
            }
        }
    }
    /**
     * sorts through the output cells and picks a random one, with probabilities based on the output value.
     * @return output move
     */
    public int outputMove(){
        int highest = 0;
        double highestNum = -1;
        for (int i=0; i<9; i++){
            if (outputCells[i]>highestNum){
                highest = i;
                highestNum = outputCells[i];
            }
        }
        return highest;
//        double sum =0;
//        for (int i=0; i<9; i++){
//            sum += outputCells[i];
//        }
//        double random = (Math.random()*sum);
//        
//        double compareAddition=0;
//        for (int i=0; i<9; i++){
//            compareAddition +=outputCells[i];
//            if (random<=compareAddition){
//                return i;
//            }
//        }
//        return -1;
    }
    
    /**
     * feeds the individual's id into the weights of the network
     * @param indi 
     */
    public void assignIndividual(Individual indi){
        individual = indi;
        int idGene=0;
        for (int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                in2secWeight[i][j]= individual.id[idGene];
                idGene+=1;
            }
        }
        for (int i=0; i<9;i++){
            for (int j=0; j<9; j++){
                sec2outWeight[i][j] = individual.id[idGene];
                idGene+=1;
            }
        }
    }
    
    
    /**
     * updates the input cells to the board inputed
     * @param board 
     */
    public void updateBoard(int[][] board){
        int iteration = 0;
        for (int i=0; i<3; i++){
            for (int j =0; j<3; j++){
                inputCells[iteration] = board[i][j];
                iteration+=1;
            }
        }
    }
    
    public void givePoints(double points){
        individual.rawScore+=points;
    }
    
    public void gameComplete(){
        individual.gamesPlayed++;
    }
}
