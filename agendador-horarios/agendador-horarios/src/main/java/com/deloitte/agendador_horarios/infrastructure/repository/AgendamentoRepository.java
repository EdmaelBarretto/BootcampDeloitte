package com.deloitte.agendador_horarios.infrastructure.repository;

import com.deloitte.agendador_horarios.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Agendamento findByServicoAndDataHoraAgendamentoBetween(String objetivo, LocalDateTime dataHoraInicio,
                                                           LocalDateTime dataHoraFinal);

    @Transactional
    void deleteByDataHoraAgendamentoAndAluno(LocalDateTime dataHoraAgendamento, String aluno);

    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);

    Agendamento findByDataHoraAgendamentoAndAluno(LocalDateTime dataHoraAgendamento, String aluno);
}