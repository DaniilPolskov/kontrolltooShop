

package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class History implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Shoe shoe;
    @OneToOne
    private Buyer buyer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date takeOnShoe;
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnShoe;

    public History() {
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Date getTakeOnShoe() {
        return takeOnShoe;
    }

    public void setTakeOnShoe(Date takeOnShoe) {
        this.takeOnShoe = takeOnShoe;
    }

    public Date getReturnShoe() {
        return returnShoe;
    }

    public void setReturnShoe(Date returnShoe) {
        this.returnShoe = returnShoe;
    }

    @Override
    public String toString() {
        return "History{" 
                + "shoe=" + shoe 
                + ", buyer=" + buyer 
                + ", takeOnShoe=" + takeOnShoe 
                +", returnShoe=" + returnShoe 
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.shoe);
        hash = 41 * hash + Objects.hashCode(this.buyer);
        hash = 41 * hash + Objects.hashCode(this.takeOnShoe);
        hash = 41 * hash + Objects.hashCode(this.returnShoe);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.shoe, other.shoe)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        if (!Objects.equals(this.takeOnShoe, other.takeOnShoe)) {
            return false;
        }
        if (!Objects.equals(this.returnShoe, other.returnShoe)) {
            return false;
        }
        return true;
    }
    
}
