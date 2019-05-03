package shared.OutsideWorld;

import communication.ChannelClient;
import static communication.ChannelPorts.NAME_GENERAL_REPOSITORY;
import static communication.ChannelPorts.PORT_GENERAL_REPOSITORY;
import entities.Manager.Interfaces.IManagerOW;
import entities.Customer.Interfaces.ICustomerOW;
import entities.Customer.Customer;
import entities.Customer.States.CustomerState;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import messages.RepositoryMessage.RepositoryMessage;

/**
 *
 * @author andre and joao
 */
public class OutsideWorld implements ICustomerOW, IManagerOW {

    private ChannelClient cc_repository;
    
    private final List<Integer> repairedCars;
    private final List<Integer> waitingForCar;
    private final String[] vehicleDriven;

    /**
     *
     * @param nCustomers
     */
    public OutsideWorld(int nCustomers) {
        this.waitingForCar = new ArrayList<>();
        this.repairedCars = new ArrayList<>();
        vehicleDriven = new String[nCustomers];
        for (int i = 0; i < nCustomers; i++) {
            if (i < 10) {
                vehicleDriven[i] = "0" + Integer.toString(i);
            } else {
                vehicleDriven[i] = Integer.toString(i);
            }
        }
        this.cc_repository = new ChannelClient(NAME_GENERAL_REPOSITORY, PORT_GENERAL_REPOSITORY);
        //updateVehicleDriven(vehicleDriven);
    }
    
    /**
     * Customer's method. The customer starts his life span in the outside world
     * until he decides do repair his car. Furthermore, he also decides if he is
     * going to need a replacement car or not.
     *
     * @param id
     * @return
     */
    @Override
    public synchronized boolean decideOnRepair(int id) {
        setCustomerState(CustomerState.NORMAL_LIFE_WITH_CAR, id);
        Random requires = new Random();
        Random n = new Random();
        int randomNum = 0;
        while (randomNum != 1) {
            randomNum = n.nextInt((100 - 1) + 1) + 1;
        }
        boolean req = requires.nextBoolean();
        if(req == true) {
            updateRequiresCar("T", id);
        }
        else {
            updateRequiresCar("F", id);
        }
        return req;
    }

    /**
     * Customer's method. After going back to work by bus, the customer waits
     * for the manager to tell him that his car has been repaired.
     *
     * @param carRepaired
     * @param id
     * @return
     */
    @Override
    public synchronized boolean backToWorkByBus(boolean carRepaired, int id) {
        setCustomerState(CustomerState.NORMAL_LIFE_WITHOUT_CAR, id);
        updateVehicleDriven("--", id);
        vehicleDriven[id] = "--";
        if (!carRepaired) {
            waitingForCar.add(id);
            notifyAll();
            while (!repairedCars.contains(id)) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
            repairedCars.remove(new Integer(id));
            return true;
        }
        return carRepaired;
    }

    /**
     * Customer's method. After going back to work by car (with replacement
     * car), the customer waits for the manager to tell him that his car has
     * been repaired.
     *
     * @param carRepaired
     * @param replacementCar
     * @param id
     * @return
     */
    @Override
    public synchronized boolean backToWorkByCar(boolean carRepaired, int replacementCar, int id) {
        setCustomerState(CustomerState.NORMAL_LIFE_WITH_CAR, id);
        if (replacementCar == -1) {
            updateVehicleDriven(Integer.toString(id), id);
            if (id < 10) {
                vehicleDriven[id] = "0" + Integer.toString(id);
            } else {
                vehicleDriven[id] = Integer.toString(id);
            }
        } else {
            updateVehicleDriven("R" + Integer.toString(replacementCar), id);
            vehicleDriven[id] = "R" + Integer.toString(replacementCar);
        }
        if (!carRepaired) {
            waitingForCar.add(id);
            notifyAll();
            while (!repairedCars.contains(id)) {
                try {
                    wait();

                } catch (InterruptedException e) {

                }
            }
            return true;
        }
        return carRepaired;
    }

    /**
     * Manager's method. After knowing that the car is ready by the mechanic,
     * the manager calls the customer to get his car back.
     *
     * @param id customer's id which car is ready to be picked up
     * @return a boolean representing if the customer is ready to pick up the
     * car
     */
    @Override
    public synchronized boolean phoneCustomer(int id) {
        while (!waitingForCar.contains(id)) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        if (waitingForCar.contains(id)) {
            repairedCars.add(id);
            notifyAll();
            waitingForCar.remove(new Integer(id));
            return true;
        } else {
            return false;
        }
    }
    
    private synchronized void setCustomerState(CustomerState state, int id) {
        RepositoryMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepositoryMessage(RepositoryMessage.SET_CUSTOMER_STATE, state.toString(), id));
        response = (RepositoryMessage) cc_repository.readObject();
        cc_repository.close(); 
    }
    
    private synchronized void updateVehicleDriven(String s, int i) {
        RepositoryMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepositoryMessage(RepositoryMessage.VEHICLE_DRIVEN, s, i));
        response = (RepositoryMessage) cc_repository.readObject();
        cc_repository.close(); 
    }
    
    private synchronized void updateRequiresCar(String s, int i) {
        RepositoryMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepositoryMessage(RepositoryMessage.REQUIRES_CAR, s, i));
        response = (RepositoryMessage) cc_repository.readObject();
        cc_repository.close();
    }
    
    private void startCommunication(ChannelClient cc) {
        while(!cc.open()) {
            try {
                Thread.sleep(1000);
            }
            catch(Exception e) {
                
            }
        }
    }
}
