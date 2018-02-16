import java.util.List;
import java.util.Random;

/**
 * A simple model of a squirrel.
 * Squirrels age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Squirrel extends Animal
{
    // Characteristics shared by all squirrels (class variables).

    // The age at which a squirrel can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a squirrel can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a squirrel breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The squirrel's age.
    private int age;

    /**
     * Create a new squirrel. A squirrel may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the squirrel will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Squirrel(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the squirrel does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newSquirrels A list to return newly born squirrels.
     */
    public void act(List<Animal> newSquirrels)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newSquirrels);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }
 
    /**
     * Make this animal act as if it was day - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    public void dayAct(List<Animal> newAnimals) {
        //
    }
    
    /**
     * Make this animal act as if it was night - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    public void nightAct(List<Animal> newAnimals){
        //
    }
    
    /**
     * Increase the age.
     * This could result in the squirrel's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this squirrel is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newSquirrels A list to return newly born squirrels.
     */
    private void giveBirth(List<Animal> newSquirrels)
    {
        // New squirrels are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Squirrel young = new Squirrel(false, field, loc);
            newSquirrels.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed(age,BREEDING_AGE) && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

}
