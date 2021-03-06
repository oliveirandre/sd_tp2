package messages.RepositoryMessage;

import java.io.Serializable;
import java.util.HashMap;
import settings.EnumPiece;
import settings.Piece;

/**
 *
 * @author André Oliveira
 * @author João Coelho
 */
public class RepositoryMessage implements Serializable {
    
    private int msg = -1;
    
    /**
     * END message.
     */
    public static final int END = 0;
    
    /**
     * SUCCESS message.
     */
    public static final int SUCCESS = 100;
    
    /**
     * MANAGER_STATE message.
     */
    public static final int MANAGER_STATE = 1;
    
    /**
     * MECHANIC_STATE message.
     */
    public static final int MECHANIC_STATE = 2;
    
    /**
     * CUSTOMER_STATE message.
     */
    public static final int CUSTOMER_STATE = 3;
    
    /**
     * SET_MANAGER_STATE message.
     */
    public static final int SET_MANAGER_STATE = 4;
    
    /**
     * SET_MECHANIC_STATE message.
     */
    public static final int SET_MECHANIC_STATE = 5;
    
    /**
     * SET_CUSTOMER_STATE message.
     */
    public static final int SET_CUSTOMER_STATE = 6;
    
    /**
     * VEHICLE_DRIVEN message.
     */
    public static final int VEHICLE_DRIVEN = 7;
    
    /**
     * REQUIRES_CAR message.
     */
    public static final int REQUIRES_CAR = 8;
    
    /**
     * REPAIRED_CAR message.
     */
    public static final int REPAIRED_CAR = 9;
    
    /**
     * NUMBER_IN_QUEUE message.
     */
    public static final int NUMBER_IN_QUEUE = 10;
    
    /**
     * WAITING_REPLACEMENT message.
     */
    public static final int WAITING_REPLACEMENT = 11;
    
    /**
     * NUMBER_CARS_REPAIRED message.
     */
    public static final int NUMBER_CARS_REPAIRED = 12;
    
    /**
     * NUMBER_NONREPCARS_PARKED message.
     */
    public static final int NUMBER_NONREPCARS_PARKED = 13;
    
    /**
     * NUMBER_REPCARS_PARKED message.
     */
    public static final int NUMBER_REPCARS_PARKED = 14;
    
    /**
     * NUMBER_SERVICES message.
     */
    public static final int NUMBER_SERVICES = 15;
    
    /**
     * NUMBER_PARTS message.
     */
    public static final int NUMBER_PARTS = 16;
    
    /**
     * NUMBER_VEHICLES_WAITING message.
     */
    public static final int NUMBER_VEHICLES_WAITING = 17;
    
    /**
     * PART_NEEDED message.
     */
    public static final int PART_NEEDED = 18;
    
    /**
     * NUMBER_PARTS_PURCHASED message.
     */
    public static final int NUMBER_PARTS_PURCHASED = 19;
    
    private String state;
    private int id;
    private int size;
    private HashMap<EnumPiece,Integer> hash;
    private boolean[] flag;
    private int[] piecesBought;
    private String[] vehicleDriven;
    private HashMap<Integer,Piece> piecesToRepair;
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     */
    public RepositoryMessage(int msg) {
        this.msg = msg;
    }
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param state state received
     */
    public RepositoryMessage(int msg, String state) {
        this.msg = msg;
        this.state = state;
    }
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param state state received
     * @param id id received
     */
    public RepositoryMessage(int msg, String state, int id) {
        this.msg = msg;
        this.state = state;
        this.id = id;
    }
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param size size received
     */
    public RepositoryMessage(int msg, int size) {
        this.msg = msg;
        this.size = size;
    }
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param hash hashmap received
     */
    public RepositoryMessage(int msg, HashMap<EnumPiece,Integer> hash) {
        this.msg = msg;
        this.hash = hash;
    }
    
        /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param piecesToRepair hashmap received
     */
    public RepositoryMessage(HashMap<Integer,Piece> piecesToRepair, int msg) {
        this.msg = msg;
        this.piecesToRepair = piecesToRepair;
    }
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param flag boolean array received
     */
    public RepositoryMessage(int msg, boolean[] flag) {
        this.msg = msg;
        this.flag = flag;
    }
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param piecesBought int array received
     */
    public RepositoryMessage(int msg, int[] piecesBought) {
        this.msg = msg;
        this.piecesBought = piecesBought;
    }
    
    /**
     * Contructor of RepositoryMessage.
     * @param msg type of message received
     * @param vehicleDriven string array received
     */
    public RepositoryMessage(int msg, String[] vehicleDriven) {
        this.msg = msg;
        this.vehicleDriven = vehicleDriven;
    }

    /**
     * Get method for msg.
     * @return msg
     */
    public int getMessageType() {
        return this.msg;
    }
    
    /**
     * Get method for state.
     * @return state
     */
    public String getState() {
        return this.state;
    }
    
    /**
     * Get method for id.
     * @return id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Get method for size.
     * @return size
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * Get method for hash.
     * @return hash
     */
    public HashMap<EnumPiece,Integer> getStock() {
        return this.hash;
    }
    
    /**
     * Get method for piecesToRepair.
     * @return hash
     */
    public HashMap<Integer,Piece> getPiecesToRepair() {
        return this.piecesToRepair;
    }
    
    /**
     * Get method for flag.
     * @return flag
     */
    public boolean[] getFlag() {
        return this.flag;
    }
    
    /**
     * Get method for piecesBought.
     * @return piecesBought
     */
    public int[] getPiecesBought() {
        return this.piecesBought;
    }
    
    /**
     * Get method for vehicleDriven.
     * @return vehicleDriven
     */
    public String[] getVehicleDriven() {
        return this.vehicleDriven;
    }
}
