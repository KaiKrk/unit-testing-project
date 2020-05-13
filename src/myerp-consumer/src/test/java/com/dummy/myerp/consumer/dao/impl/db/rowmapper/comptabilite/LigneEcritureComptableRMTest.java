package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.LigneEcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class LigneEcritureComptableRMTest {
    @Mock
    private ResultSet resultSet;

    @Mock
    private static DaoProxy daoProxy;

    @Mock
    private static ComptabiliteDao comptabiliteDao;

    @Before
    public void beforeTest(){
        ConsumerHelper.configure(daoProxy);
        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
    }

    @Test
    public void mapRow() throws SQLException {
        Mockito.when(resultSet.getBigDecimal("credit")).thenReturn(BigDecimal.valueOf(250));
        Mockito.when(resultSet.getBigDecimal("debit")).thenReturn(BigDecimal.valueOf(700));
        Mockito.when(resultSet.getString("libelle")).thenReturn("Achat");

        LigneEcritureComptableRM ligneEcritureComptableRM = new LigneEcritureComptableRM();
        LigneEcritureComptable resultLigneEcritureComptableRM = ligneEcritureComptableRM.mapRow(resultSet,0);

        Assert.assertEquals(resultLigneEcritureComptableRM.getDebit(),BigDecimal.valueOf(700));
        Assert.assertEquals(resultLigneEcritureComptableRM.getCredit(), BigDecimal.valueOf(250));
        Assert.assertEquals(resultLigneEcritureComptableRM.getLibelle(),"Achat");
    }
}
