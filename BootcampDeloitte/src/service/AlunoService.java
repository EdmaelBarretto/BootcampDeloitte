package service;

import model.Aluno;
import java.util.ArrayList;

public class AlunoService {

    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    public void listarAlunos() {
        for (Aluno aluno : alunos) {
            aluno.exibirAluno();
        }
    }

    public Aluno buscarAluno(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }
        return null;
    }

    public void atualizarAluno(int id, String novoNome) {
        Aluno aluno = buscarAluno(id);

        if (aluno != null) {
            aluno.setNome(novoNome);
            System.out.println("Aluno atualizado!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void removerAluno(int id) {
        Aluno aluno = buscarAluno(id);

        if (aluno != null) {
            alunos.remove(aluno);
            System.out.println("Aluno removido.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
}