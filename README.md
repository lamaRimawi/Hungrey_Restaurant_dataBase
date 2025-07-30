# # Hungry Restaurant Database

A comprehensive database system for managing restaurant operations including customer orders, menu management, staff coordination, and billing processes.

## About

This project implements a relational database management system specifically designed for restaurant operations. The system handles everything from customer information and menu items to order processing and staff management, providing a complete solution for restaurant data management.

## Features

- 🍽️ **Menu Management** - Organize food items, categories, prices, and descriptions
- 👥 **Customer Records** - Store customer information and order history  
- 📋 **Order Processing** - Track orders from placement to completion
- 🏪 **Table Management** - Handle table reservations and seating arrangements
- 👨‍🍳 **Staff Management** - Manage employee information and roles
- 💰 **Billing System** - Generate bills and process payments
- 📊 **Reports** - Generate sales and performance analytics

## Database Schema

### Main Tables
- **Customers** - Customer details and contact information
- **Menu_Items** - Food items with prices and categories
- **Orders** - Order information and status tracking
- **Order_Items** - Individual items within each order
- **Tables** - Restaurant table information
- **Staff** - Employee data and role assignments
- **Bills** - Payment and billing records

## Getting Started

### Prerequisites
- MySQL 5.7+ or PostgreSQL 10+
- Database management tool (MySQL Workbench, pgAdmin, etc.)

### Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/lamaRimawi/Hungrey_Restaurant_dataBase.git
   cd Hungrey_Restaurant_dataBase
   ```

2. **Create database:**
   ```sql
   CREATE DATABASE hungry_restaurant;
   USE hungry_restaurant;
   ```

3. **Run SQL scripts:**
   Execute the SQL files in order:
   - Create tables
   - Insert sample data
   - Set up constraints and indexes

## Sample Queries

**View all menu items by category:**
```sql
SELECT category, item_name, price 
FROM menu_items 
ORDER BY category, price;
```

**Find customer order history:**
```sql
SELECT c.customer_name, o.order_date, o.total_amount
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
WHERE c.customer_name = 'John Smith';
```

**Check table availability:**
```sql
SELECT table_number, capacity, status
FROM tables
WHERE status = 'Available';
```

**Daily sales report:**
```sql
SELECT DATE(order_date) as date, COUNT(*) as orders, SUM(total_amount) as revenue
FROM orders
WHERE order_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY DATE(order_date);
```

## Database Design

- **Normalized Structure** - 3NF compliance for data integrity
- **Foreign Key Relationships** - Maintains referential integrity
- **Indexed Columns** - Optimized for common queries
- **Data Validation** - Constraints ensure data quality

## Usage Examples

This database supports typical restaurant operations:
- Adding new menu items and updating prices
- Recording customer orders and preferences
- Tracking table reservations and availability
- Managing staff schedules and roles
- Generating daily/monthly sales reports
- Analyzing popular menu items and customer trends

## Project Structure

```
Hungrey_Restaurant_dataBase/
├── sql/
│   ├── create_tables.sql    # Table creation scripts
│   ├── insert_data.sql      # Sample data
│   └── queries.sql          # Common queries
├── docs/
│   ├── ER_diagram.png       # Database design
│   └── requirements.md      # Project specifications
└── README.md
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature/new-feature`)
6. Create a Pull Request

## Future Enhancements

- Integration with POS systems
- Online ordering capabilities
- Inventory management
- Customer loyalty programs
- Multi-location support

## License

This project is available under the MIT License.

## Contact

**Lama Rimawi**  
GitHub: [@lamaRimawi](https://github.com/lamaRimawi)

For questions or suggestions, please open an issue on GitHub.
