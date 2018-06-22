/*
 * Created by WilliamBD
 * On 19/06/2018
 * To integrate with the Neural Networks and play TIC TAC TOE
 */

package tictactoebot;


/**
 *
 * @author widea9928
 */
public class Individual implements Comparable{
    //saves the score of the individual
    int rawScore =0;
    double gamesPlayed=1;
    double fitness = rawScore;
    
    double[] id = new double[162];
    
    /**
     * generates a random id for the Individual.
     * each gene is between -10 and 10.
     */
    public void randomID(){
        for (int i=0; i<162;i++){
            id[i]=((Math.random()*20)-10);
        }
    }
    
    /**
     * mutates the already set id by random numbers
     */
    public void mutate(){
        for (int i=0; i<162;i++){
            id[i] += Math.random()-0.5;
        }
    }
    /**
     * copies newID as the individuals id.
     * @param newID 
     * 
     */
    public void setId(double[] newID){
        //Netbeans tried this useful thing to copy the ID
        System.arraycopy(newID, 0, id, 0, 162);
    }
    
     /**
     * assigns points to the game individual for fitness asesment.
     * @param points
     */
    public void givePoints(double points){
        rawScore+=points;
        fitness = rawScore;
    }
    
    /**
     * clears the score of the player
     */
    public void clearPoints(){
        rawScore = 0;
    }

    @Override
    public int compareTo(Object o) {
        return (((Individual)o).rawScore-this.rawScore);
        
    }

    



}
