package org.example.scripts;

import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;
import org.example.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    /*@Autowired
    private javax.sql.DataSource dataSource;
   */

    @PostConstruct
    public void init(){
        TransactionTemplate transactionTmp = new TransactionTemplate(txManager);
        transactionTmp.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                UnitOfWork uow = DefaultUnitOfWork.startAndGet();
                repository.add(new ShoppingCart("1"));
                repository.add(new ShoppingCart("2"));
                uow.commit();
            }
        });

        /*JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<ShoppingCart> carts = new ArrayList<>();

        final List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM cart");
        for (Map row : rows) {
            ShoppingCart cart = new ShoppingCart();
            cart.setId((String)row.get("id"));
            cart.setQuantity((Integer) row.get("quantity"));

            System.out.println("cart --> " + cart);

            carts.add(cart);
        }*/


    }
}
