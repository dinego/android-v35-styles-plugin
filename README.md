# Cordova Plugin Android V35 Styles

Plugin Cordova para aplicar configurações específicas do Android API 35, incluindo suporte para display cutout (notch) e estilos otimizados.

## Funcionalidades

- ✅ Configura automaticamente o target para API 35
- ✅ Aplica estilos específicos para `windowLayoutInDisplayCutoutMode`
- ✅ **Configuração padrão: `never` - App nunca fica atrás do notch**
- ✅ Suporte para dispositivos com notch/cutout
- ✅ Hooks automáticos para configuração
- ✅ Métodos JavaScript para controle programático

## Instalação

### Adicionar o plugin ao projeto:

```bash
cordova plugin add android-v35-styles-plugin
```

### Ou instalar localmente:

```bash
cordova plugin add ./android-v35-styles-plugin
```

## Uso

### Configuração Automática

O plugin aplica automaticamente as configurações durante o build. **Por padrão, o app nunca ficará atrás do notch (área do relógio, ícones de rede, etc.)**.

### Uso Programático

```javascript
// Verificar se o dispositivo suporta API 35
AndroidV35Styles.isV35Supported(
    function(supported) {
        console.log('API 35 suportado:', supported);
    },
    function(error) {
        console.error('Erro:', error);
    }
);

// Aplicar configurações de API 35 (padrão: never)
AndroidV35Styles.applyV35Configurations(
    function(success) {
        console.log('Configurações aplicadas:', success);
    },
    function(error) {
        console.error('Erro:', error);
    }
);

// Obter modo atual do display cutout
AndroidV35Styles.getDisplayCutoutMode(
    function(mode) {
        console.log('Modo atual:', mode);
    },
    function(error) {
        console.error('Erro:', error);
    }
);

// Definir modo do display cutout
AndroidV35Styles.setDisplayCutoutMode('never', // ou 'shortEdges', 'longEdges'
    function(success) {
        console.log('Modo definido:', success);
    },
    function(error) {
        console.error('Erro:', error);
    }
);
```

## Modos de Display Cutout

- **`never`** (PADRÃO): **Nunca permite conteúdo na área do notch** - App nunca fica atrás do relógio/ícones
- **`shortEdges`**: Permite conteúdo nas bordas curtas (topo/base)
- **`longEdges`**: Permite conteúdo nas bordas longas (laterais)
- **`default`**: Comportamento padrão do sistema

## Comportamento Padrão

O plugin está configurado para usar o modo `never` por padrão, garantindo que:
- ✅ O app nunca fique atrás do notch
- ✅ O conteúdo nunca seja cortado pela área do relógio
- ✅ Os ícones de rede, bateria, etc. sempre fiquem visíveis
- ✅ Melhor experiência do usuário em dispositivos com notch

## Arquivos Criados/Modificados

- `platforms/android/project.properties` - Target API 35
- `platforms/android/app/src/main/res/values-v35/styles.xml` - Estilos específicos com `windowLayoutInDisplayCutoutMode="never"`

## Requisitos

- Cordova >= 9.0.0
- cordova-android >= 10.0.0
- Android API 28+ (para funcionalidades de cutout)

## Licença

Apache 2.0 