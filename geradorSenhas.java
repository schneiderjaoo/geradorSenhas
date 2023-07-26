import java.security.SecureRandom;
import java.util.Random;

public class GeradorDeSenhas {

    private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String LETRAS_MAIUSCULAS = LETRAS_MINUSCULAS.toUpperCase();
    private static final String CARACTERES_ESPECIAIS = "!@#$%^&*()-_=+[{]};:'\",<.>/?";
    private static final String NUMEROS = "0123456789";
    private static final String CARACTERES_PERMITIDOS = LETRAS_MINUSCULAS + LETRAS_MAIUSCULAS + CARACTERES_ESPECIAIS + NUMEROS;

    public static void main(String[] args) {
        int tamanhoSenha = 12;
        String senha = gerarSenha(tamanhoSenha);
        imprimirSenha(senha);
    }

    private static String gerarSenha(int tamanho) {
       if (tamanho < 8) {
            throw new IllegalArgumentException("O tamanho mínimo da senha é 8.");
        }

        SecureRandom secureRandom = new SecureRandom();
        Random random = new Random();

        StringBuilder senha = new StringBuilder();
        
        // Adicionar pelo menos uma letra maiúscula
        senha.append(LETRAS_MAIUSCULAS.charAt(secureRandom.nextInt(LETRAS_MAIUSCULAS.length())));
        
        // Adicionar pelo menos uma letra minúscula
        senha.append(LETRAS_MINUSCULAS.charAt(secureRandom.nextInt(LETRAS_MINUSCULAS.length())));
        
        // Adicionar pelo menos um caractere especial
        senha.append(CARACTERES_ESPECIAIS.charAt(secureRandom.nextInt(CARACTERES_ESPECIAIS.length())));
        
        // Adicionar pelo menos um dígito numérico
        senha.append(NUMEROS.charAt(secureRandom.nextInt(NUMEROS.length())));
        
        int caracteresFaltantes = tamanho - senha.length();

        // Adicionar os caracteres restantes da senha
        for (int i = 0; i < caracteresFaltantes; i++) {
            senha.append(CARACTERES_PERMITIDOS.charAt(random.nextInt(CARACTERES_PERMITIDOS.length())));
        }

        // Embaralhar a senha para garantir que os caracteres especiais não fiquem agrupados
        return shuffleString(senha.toString());
    }

    // Método para embaralhar a senha
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        Random random = new Random();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
//printa a senha na tela
    private static void imprimirSenha(String senha) {
        System.out.println("Senha gerada: " + senha);
    }
}
