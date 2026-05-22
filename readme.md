# Virtable

Projeto Java criado com Springboot

API Rest de um

## Configuração Local

### 1 - Instalar o Scoop
Scoop é uma aplicação de linha de comando para instalar programas

#### Execute no PowerShell

```
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression
```

### 2 - Instalar o JDK e o Maven

```
scoop install main/maven
scoop bucket add java
scoop install java/openjdk
```