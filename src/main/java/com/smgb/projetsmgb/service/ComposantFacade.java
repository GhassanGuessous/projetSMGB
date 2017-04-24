package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Composant;
import com.smgb.projetsmgb.bean.DomaineAssocie;
import com.smgb.projetsmgb.bean.ProvideInterface;
import com.smgb.projetsmgb.bean.ProvideInterfaceItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Ghassan
 */
@Stateless
public class ComposantFacade extends AbstractFacade<Composant> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @EJB
    ProvideInterfaceItemFacade provideInterfaceItemFacade;
    @EJB
    ProvideInterfaceFacade provideInterfaceFacade;
    @EJB
    InputFacade inputFacade;
    @EJB
    OutputFacade outputFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComposantFacade() {
        super(Composant.class);
    }

    public Object[] findByDomaineAndNom(Composant composant) {
        String requete = "SELECT c FROM Composant c WHERE c.domaineAssocie.id = " + composant.getDomaineAssocie().getId() + " AND c.nom = '" + composant.getNom() + "'";
        List<Composant> composants = em.createQuery(requete).getResultList();
        if (!composants.isEmpty()) {
            return new Object[]{1, composants.get(0)};
        } else {
            ProvideInterface provideInterface = new ProvideInterface();
            Long i = provideInterfaceFacade.generateId("ProvideInterface", "id");
            provideInterface.setNom(composant.getNom());
            composant.setProvideInterface(provideInterface);
            create(composant);
            provideInterfaceFacade.create(provideInterface);
            return new Object[]{-1, composant};
        }
    }
    
    public List<Composant> findComposantsBySubDomaine(DomaineAssocie subDomaine){
        return em.createQuery("SELECT c FROM Composant c WHERE c.domaineAssocie.id = " + subDomaine.getId()).getResultList();
    }
    
    public TreeNode createComposant(DomaineAssocie subDomaine) {
        List<Composant> composants = findComposantsBySubDomaine(subDomaine);
        TreeNode root = new DefaultTreeNode("Root1", null);
        for (Composant composant : composants) {
            TreeNode composants1 = new DefaultTreeNode(composant.getNom(), root);
            List<ProvideInterfaceItem> provideInterfaceItems = provideInterfaceItemFacade.findProvideInterfaceItemByComposant(composant);
            for (ProvideInterfaceItem provideInterfaceItem : provideInterfaceItems) {
                TreeNode provideInterface = new DefaultTreeNode(provideInterfaceItem.getNom(), composants1);
            }
        }
        return root;
    }
}