package com.gabrielrocha;

import com.gabrielrocha.model.Disciplina;
import com.gabrielrocha.model.Professor;
import com.gabrielrocha.model.Turma;
import com.gabrielrocha.service.DisciplinaService;
import com.gabrielrocha.service.ProfessorService;
import com.gabrielrocha.service.TurmaService;
import corejava.Console;

import java.util.List;

public class PrincipalTurma {
    private final TurmaService turmaService = new TurmaService();
    private final ProfessorService professorService = new ProfessorService();
    private final DisciplinaService disciplinaService = new DisciplinaService();

    public void principal(){
        int idProfessor;
        int idDisciplina;
        int ano;
        int periodo;
        boolean continua = true;

        while (continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma nova turma");
            System.out.println("2. Remover uma turma");
            System.out.println("3. Listar todos as turmas");
            System.out.println("4. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            System.out.println();

            switch (opcao){
                case 1->{
                    List<Professor> professors = professorService.recuperarTodos();
                    for(Professor aux: professors){
                        System.out.println("Professor: " + aux.getNome()+ "  id: " + aux.getId());
                    }
                    idProfessor = Console.readInt('\n' + "Digite o id do professor da turma dentre os professores acima: ");

                    List<Disciplina> disciplinas = disciplinaService.recuperarTodos();
                    for(Disciplina aux: disciplinas){
                        System.out.println("Disciplina: " + aux.getNome()+ "  id: " + aux.getId());
                    }
                    idDisciplina = Console.readInt('\n' + "Digite o id da disciplina da turma dentre as Disciplinas acima: ");
                    ano = Console.readInt("Digite o ano da turma: ");
                    periodo = Console.readInt("Digite o periodo da turma: ");
                    Disciplina disciplina = disciplinaService.recuperarPorId(idDisciplina);
                    Professor professor = professorService.recuperarPorId(idProfessor);
                    Turma turma = new Turma(ano, periodo, professor, disciplina);
                    turmaService.incluir(turma);
                }
                case 2->{
                    int idTurma = Console.readInt("Digite o id da turma a ser removida: ");
                    turmaService.remover(idTurma);
                }
                case 3->{
                    for(Turma turma: turmaService.recuperarTodos()){
                        System.out.println( turma.getProfessor().getNome() + '\n' + turma.getDisciplina().getNome() + '\n' + turma.toString() + '\n' + "alunos inscritos: " + turma.getInscricaos().size());
                    }
                }
                case 4 ->{
                    continua = false;
                }
                default -> System.out.println("Opcao invalida" + '\n');
            }



        }

    }
}