package org.example.scripts;

import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;
import org.example.model.CartItem;
import org.example.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Component
public class Db {

    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @Autowired
    private Repository repository;

    @Autowired
    private javax.sql.DataSource dataSource;

    @PostConstruct
    public void init(){
        TransactionTemplate transactionTmp = new TransactionTemplate(txManager);
        transactionTmp.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                UnitOfWork uow = DefaultUnitOfWork.startAndGet();
                final ShoppingCart shoppingCart1 = new ShoppingCart(1);
                final ShoppingCart shoppingCart2 = new ShoppingCart(2);

                final CartItem item1 = new CartItem("item1", "soap", "Dove", "Beauty Soap", 1);
                final CartItem item2 = new CartItem("item1", "soap", "Dove", "Beauty Soap", 1);

                item1.setShoppingcart(shoppingCart1);
                item2.setShoppingcart(shoppingCart2);

                shoppingCart1.addItem(item1);
                shoppingCart2.addItem(item2);

                repository.add(shoppingCart1);
                repository.add(shoppingCart2);
                uow.commit();
            }
        });

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate
                .execute("create table shopping_view (CART_Id INTEGER , ITEM_ID VARCHAR, NAME VARCHAR, CODE VARCHAR, DESCRIPTION VARCHAR, QUANTITY INTEGER)");

        jdbcTemplate.update("insert into shopping_view (CART_Id, ITEM_ID, NAME, CODE, DESCRIPTION, QUANTITY) values (?,?,?,?,?,?)", new Object[] {1,"item1", "soap","Dove","Beauty Soap",1});
        jdbcTemplate.update("insert into shopping_view (CART_Id, ITEM_ID, NAME, CODE, DESCRIPTION, QUANTITY) values (?,?,?,?,?,?)", new Object[] {2,"item1", "soap","Dove", "Beauty Soap",1});


    }
}
