# Virtable

Projeto Java criado com Springboot

API Rest de um sistema desenvolvido para auxiliar na execução e armazenar informações de campanhas de diversos sistemas RPG

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

## Endpoints

Com o arquivo `Insomnia.yaml` é possivel importar requests genericos para cada uma das endpoints em aplicativos de Cliente de API 

Para executa-las corretamente é preciso definir duas variáveis de ambiente

| Nome     | Descrição                                      | Exemplo               |
| :------- | :--------------------------------------------- | :-------------------- |
| base_url | dominio onde o aplicativo esta sendo executado | http://localhost:8080 |
| token    | string que o servidor retorna ao autenticar    | caracteres aleatorios |
