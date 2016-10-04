/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table_models;

import entities.Compte;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import jpa.CompteJpaController;

/**
 *
 * @author Ulrich TIAYO SwingBS
 */
public class CompteTableModel extends AbstractTableModel {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TutoTransactionsPU");
    private final CompteJpaController compteJpaController = new CompteJpaController(emf);
    private final String[] titles = {"id", "Nom", "Solde"};
    private final List<Compte> comptes;

    public CompteTableModel() {
        comptes = compteJpaController.findCompteEntities();
    }

    public CompteTableModel(List<Compte> comptes) {
        this.comptes = comptes;
    }

    @Override
    public int getRowCount() {
        return comptes.size();
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public Object getValueAt(int i, int j) {
        switch (j) {
            case 0:
                return i + 1;
            case 1:
                return comptes.get(i).getNomCompte();
            case 2:
                return comptes.get(i).getSolde();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return titles[i];
    }

    public void addCompte(Compte compte) {
        compteJpaController.create(compte);
        comptes.add(compte);
        fireTableDataChanged();
    }

    public Compte getCompteAt(int index) {
        if (index > comptes.size()) {
            return null;
        }
        return comptes.get(index);
    }

}
