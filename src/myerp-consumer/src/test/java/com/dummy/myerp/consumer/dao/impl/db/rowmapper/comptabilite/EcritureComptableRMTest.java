package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class EcritureComptableRMTest {

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
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("journal_code")).thenReturn("AC");
        Mockito.when(resultSet.getString("reference")).thenReturn("AC-2020/00102");
        Mockito.when(resultSet.getString("libelle")).thenReturn("Achat");

        EcritureComptableRM ecritureComptableRM = new EcritureComptableRM();
        EcritureComptable resultEcritureComptable = ecritureComptableRM.mapRow(resultSet,0);

        Assert.assertTrue("check if id is equal 1", resultEcritureComptable.getId()== 1);
        Assert.assertEquals(resultEcritureComptable.getLibelle(), "Achat");
    }


}
