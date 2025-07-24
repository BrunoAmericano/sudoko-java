# 🎯 Ctrl+Alt+Sudoku

![Build](https://github.com/BrunoAmericano/sudoku-java/actions/workflows/java-ci.yml/badge.svg)

> 🧠 **“Sudoku não é apenas um jogo, é um exercício de lógica que mantém sua mente tão afiada quanto seu código.”**

---

## ✨ Sobre o projeto

Este é o **Sudoku em Java** desenvolvido como parte do meu processo de aprendizado avançado. Possui:

✅ Geração aleatória de tabuleiros  
✅ Solução automática integrada  
✅ Interface gráfica (Swing) intuitiva  
✅ Estrutura progressiva de níveis de dificuldade (Easy, Medium, Hard)  
✅ Código limpo e modular seguindo boas práticas de OO

---

## 🚀 Como executar

### 🔧 Pré-requisitos

- Java 21+
- Git

### 🖥️ Passo a passo

```bash
# Clone este repositório
git clone https://github.com/BrunoAmericano/sudoku-java.git
cd sudoku-java

# Compile os arquivos
javac -d out $(find src -name "*.java")

# Crie o jar executável
jar cfe sudoku-java.jar br.com.seuprojeto.sudoku.Main -C out .

# Execute o jogo
java -jar sudoku-java.jar
```

### 🧩 Funcionalidades
💡 Game progression inteligente

🕹️ GUI simples e responsiva com Swing

📝 Código orientado a objetos, pronto para testes unitários e expansão

### 🛠️ Tecnologias
☕ Java 21 (OpenJDK)

🐙 Git e GitHub Actions

🖌️ Swing para GUI

