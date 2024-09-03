package click.gestao.api.repository;

import click.gestao.api.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}
