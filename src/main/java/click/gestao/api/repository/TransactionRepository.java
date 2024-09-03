package click.gestao.api.repository;

import click.gestao.api.domain.Transactions.Transaction;
import click.gestao.api.domain.Transactions.TypeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.type_transaction = :typeTransaction")
    Page<Transaction> findByTypeTransaction(TypeTransaction typeTransaction, Pageable pageable);
}
