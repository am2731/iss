package model;

public class Entity<ID>{

    private ID id;

    /**
     * getter for the id
     * @return: ID, the id
     */
    public ID getId(){
        return id;
    }

    /**
     * setter for the id
     * @param id: ID, representing the new id
     */
    public void setId(ID id){
        this.id = id;
    }
}
