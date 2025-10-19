# Campo Minado 🎮

Um jogo clássico de Campo Minado desenvolvido em Java como parte do evento **Fatec Portas Abertas**.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido para demonstrar conceitos de programação orientada a objetos e desenvolvimento de interfaces gráficas durante o evento Fatec Portas Abertas. O jogo implementa todas as funcionalidades clássicas do Campo Minado com uma interface amigável e recursos visuais.

**Desenvolvedor:** Lucas Sartorelli  
**GitHub:** [@VonLanplace](https://github.com/VonLanplace)

## 🎯 Objetivo do Jogo

Descubra todas as casas seguras sem detonar nenhuma mina! O tabuleiro contém minas escondidas e números que indicam quantas minas existem nas casas vizinhas.

## 🕹️ Como Jogar

### Clique Esquerdo
- **Em casa vazia:** Revela a casa
- **Em casa segura:** Pode revelar várias casas de uma vez

### Clique Direito
- **Primeiro clique:** Marca com "!" - indica mina suspeita
- **Segundo clique:** Remove marcação

### Entenda os Números
- **1:** Há 1 mina nas 8 casas ao redor
- **2:** Há 2 minas nas casas vizinhas
- E assim por diante...

### Contadores
- **Minas (Esquerdo):** Quantas minas faltam marcar
- **Marcadas (Direito):** Quantas minas já foram marcadas

### Dicas Estratégicas
- **Comece pelos cantos:** As bordas e cantos têm menos vizinhos, facilitando a dedução
- **Use a lógica:** 
  - Se um "1" tem apenas uma casa não revelada ao redor, essa casa é uma mina!
  - Se o número na casa é igual à quantidade de casas não reveladas ao redor, todas são minas

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Eclipse IDE**
- **Bibliotecas:**
  - JGoodies Common (v1.8.1)
  - JGoodies Forms (v1.9.0)
  - MigLayout Core & Swing (v11.4.2)

## 📁 Estrutura do Projeto

```
CampoMinado/
├── src/
│   ├── controller/     # Controladores do jogo
│   │   ├── BuzzerBiip.java
│   │   ├── CtrCampo.java
│   │   ├── CtrGame.java
│   │   └── CtrMain.java
│   ├── model/          # Modelos e componentes
│   │   ├── AjudaPanel.java
│   │   ├── MainButtons.java
│   │   ├── MarioBeep.java
│   │   ├── MarkedButton.java
│   │   ├── MarkedIcon.java
│   │   └── MineButton.java
│   └── view/           # Interface gráfica
│       ├── FieldSquare.java
│       ├── GameMessages.java
│       ├── GameSecreen.java
│       ├── HelpWindow.java
│       └── Main.java
├── lib/                # Bibliotecas externas
└── README.md
```

## 🚀 Como Executar

### Opção 1: Usando Eclipse (Recomendado)
1. Certifique-se de ter o **Java 21** instalado
2. Clone o repositório:
   ```bash
   git clone https://github.com/VonLanplace/campo-minado.git
   ```
3. Abra o projeto no Eclipse
4. Execute a classe `Main.java` localizada em `src/view/Main.java`

### Opção 2: Compilação Manual via Terminal

#### Pré-requisitos
- Java JDK 21 instalado
- Variável de ambiente JAVA_HOME configurada

#### Passos para compilação manual:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/VonLanplace/campo-minado.git
   cd campo-minado
   ```

2. **Crie a estrutura de diretórios para compilação:**
   ```bash
   mkdir -p build/classes
   ```

3. **Compile todos os arquivos Java:**
   ```bash
   javac -cp "lib/*" -d build/classes src/controller/*.java src/model/*.java src/view/*.java
   ```

4. **Execute o jogo:**
   ```bash
   java -cp "build/classes:lib/*" view.Main
   ```

#### Script de compilação automática (Linux/Mac):

Crie um arquivo `compile.sh`:
```bash
#!/bin/bash
echo "Compilando Campo Minado..."
mkdir -p build/classes
javac -cp "lib/*" -d build/classes src/controller/*.java src/model/*.java src/view/*.java
if [ $? -eq 0 ]; then
    echo "Compilação bem-sucedida!"
    echo "Executando o jogo..."
    java -cp "build/classes:lib/*" view.Main
else
    echo "Erro na compilação!"
fi
```

#### Script de compilação automática (Windows):

Crie um arquivo `compile.bat`:
```batch
@echo off
echo Compilando Campo Minado...
mkdir build\classes 2>nul
javac -cp "lib\*" -d build\classes src\controller\*.java src\model\*.java src\view\*.java
if %errorlevel% equ 0 (
    echo Compilação bem-sucedida!
    echo Executando o jogo...
    java -cp "build\classes;lib\*" view.Main
) else (
    echo Erro na compilação!
)
```

## 📦 Dependências

As seguintes bibliotecas estão incluídas no projeto:
- `com.jgoodies.common_1.8.1.v20240327-0800.jar`
- `com.jgoodies.forms_1.9.0.v20240327-0800.jar`
- `com.miglayout.core_11.4.2.jar`
- `com.miglayout.swing_11.4.2.jar`

## 🎉 Recursos Implementados

- ✅ Interface gráfica intuitiva
- ✅ Sistema de marcação de minas
- ✅ Contadores de minas e marcações
- ✅ Efeitos sonoros
- ✅ Sistema de ajuda integrado
- ✅ Lógica completa do jogo
- ✅ Detecção de vitória/derrota

## 🔧 Solução de Problemas

### Erro comum: "Class not found"
- Verifique se todas as bibliotecas estão na pasta `lib/`
- Confirme que o classpath inclui todos os JARs necessários

### Erro de compilação
- Certifique-se de estar usando Java JDK 21
- Verifique se não há caracteres especiais nos caminhos dos arquivos

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais como parte do evento Fatec Portas Abertas.

---

*Desenvolvido por Lucas Sartorelli*
