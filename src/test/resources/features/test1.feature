@tag
Feature: Modelos de auto

  @getNombreModeloAuto
  Scenario Outline: Validar el nombre de modelo de auto
    Given Ingreso a la web de demotest
    When selecciono la clasificacion "<clasificacion>"
    And selecciono la marca "<marca>" y modelo "<modelo>" del auto
    Then debe de tener el nombre de modelo "<modelo>"

    Examples: 
      | clasificacion  | marca       | modelo              |
      | Overall Rating | Lamborghini | Diablo              |
      | Overall Rating | Pagani      | Zonda               |
      | Overall Rating | Alfa Romeo  | Guilia Quadrifoglio |
