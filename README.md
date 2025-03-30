Sistema de gestión de reservas para hoteles implementado en Java usando el patrón MVC (Modelo-Vista-Controlador).

## Características Principales

- 🏨 **Gestión de habitaciones**
  - 3 plantas con 5 habitaciones cada una
  - Tipos: Individual, Doble, Suite
  - Estados: Disponible, Reservada, Ocupada
- 👤 **Gestión de clientes**
  - IDs automáticos secuenciales (CLI-001, CLI-002, ...)
  - Historial de reservas
  - Límite de 3 reservas activas
- 📅 **Sistema de reservas**
  - Validación de fechas
  - Cálculo automático de precios
  - Cancelación de reservas
- ✅ **Validaciones**
  - Solapamiento de fechas
  - Límites de reservas
  - Restricciones temporales
- 📊 **Resúmenes**
  - Estado general del hotel
  - Habitaciones por tipo/estado
  - Reservas activas/historial