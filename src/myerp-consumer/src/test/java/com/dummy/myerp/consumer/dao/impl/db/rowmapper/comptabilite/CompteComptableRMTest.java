package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;


import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class CompteComptableRMTest {
    @Mock
    private ResultSet resultSet;

    @Test
    public void mapRow() throws SQLException {

        Mockito.when(resultSet.getInt("numero")).thenReturn(25);
        Mockito.when(resultSet.getString("libelle")).thenReturn("Achat");

        CompteComptableRM compteComptableRM = new CompteComptableRM();
        CompteComptable compteComptable = compteComptableRM.mapRow(resultSet, 0);

        Assert.assertEquals(compteComptable.getNumero().intValue(), 25);
        Assert.assertEquals(compteComptable.getLibelle(), "Achat");
    }
}
