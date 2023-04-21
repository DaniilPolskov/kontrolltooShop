

package session;

import entity.Shoe;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ShoeFacade extends AbstractFacade<Shoe> {

    @PersistenceContext(unitName = "SPTV21WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoeFacade() {
        super(Shoe.class);
    }

}
