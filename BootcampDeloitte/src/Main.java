import model.Aluno;
import service.AlunoService;

public class Main {

    public static void main(String[] args) {

        AlunoService service = new AlunoService();

        Aluno a1 = new Aluno(1, "Maria", 25, "Hipertrofia");
        Aluno a2 = new Aluno(2, "João", 30, "Emagrecimento");

        service.cadastrarAluno(a1);
        service.cadastrarAluno(a2);

        System.out.println("Lista de alunos:");
        service.listarAlunos();

        service.atualizarAluno(1, "Maria Silva");

        service.removerAluno(2);

        System.out.println("Lista atualizada:");
        service.listarAlunos();
    }
}