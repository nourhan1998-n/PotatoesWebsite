<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>

    <!-- Bootstrap CSS for Modals and Styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- Custom CSS -->
    <style>
        /* Sidebar CSS */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }

        .admin-panel {
            display: flex;
        }

        .sidebar {
            width: 25%;
            background: #28a745;
            color: white;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar ul li {
            padding: 15px;
            border-bottom: 1px solid #495057;
        }

        .sidebar ul li a {
            color: white;
            text-decoration: none;
            font-size: 18px;
        }

        .sidebar ul li:hover {
            background: #495057;
        }

        /* Dashboard Content */
        .dashboard-content {
            width: 100%;
            padding: 5px;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-left: 5%;
        }

        .dashboard-content h2 {
            color: #333;
            font-size: 28px;
        }

        .dashboard-content table {
            width: 100%;
            border-collapse: collapse;
        }

        .dashboard-content table, th, td {
            border: 1px solid #ddd;
        }

        .dashboard-content th, td {
            padding: 12px;
            text-align: left;
        }

        .dashboard-content th {
            background-color: #28a745;
            color: white;
        }

        .dashboard-content tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .dashboard-content tr:hover {
            background-color: #f1f1f1;
        }

        /* Button Styles */
        .btn {
            display: inline-block;
            padding: 10px 20px;
            color: white;
            background: #28a745;
            border: none;
            cursor: pointer;
            margin-top: 10px;
            text-decoration: none;
        }

        .btn-edit {
            background-color: #ffc107;
        }

        .btn-delete {
            background-color: #dc3545;
        }

        .btn:hover {
            opacity: 0.9;
        }

        /* Modal Content */
        .modal-header {
            background-color: #28a745;
            color: white;
        }
    </style>
</head>
<body>

    <div class="admin-panel">
        <div class="sidebar">
            <ul>
                <li><a href="adminDashboard">Products</a></li>
                <li><a href="AdminDashboard/addProduct.jsp">Add Product</a></li>
                <li><a href="viewUsers">Customers</a></li>
                <li><a href="AdminDashboard/adminLogin.jsp">Logout</a></li>
            </ul>
        </div>

         <!-- Bootstrap JS -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>