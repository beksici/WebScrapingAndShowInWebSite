<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ürün Özelliği</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        h1 {
            background-color: aqua;
        }

        h2 {

            background-color: chartreuse;
        }

        table {
            background-color: darkgray;
        }
    </style>
</head>

<body>

    <h1 style="text-align: center;">Ürün Özellikleri </h1>
    <hr>
    <table class="table" border="1">
        <thead style="text-align: center;">
            <tr>
                <th>Marka</th>
                <th>Model</th>
                <th>Fiyat</th>
                <th>İşletim Sistemi</th>
                <th>İşlemci Tipi</th>
                <th>İşlemci Nesli</th>
                <th>SSD</th>
                <th>HDD</th>
                <th>Ram</th>
                <th>Ekran Boyutu</th>
                <th>Puan</th>
            </tr>

        </thead>
        <tbody style="text-align:center ;">
            <?php
            $servername = "localhost";
            $username = "root";
            $password = "Mysql123";
            $database = "pcdb1";
            $connection = new mysqli($servername, $username, $password, $database);
            if ($connection->connect_error) {
                die("connection Failed" . $connection->connect_error);
            }


            $model_id = htmlspecialchars($_GET['model_id']);
            $urun_title = htmlspecialchars($_GET['urun_title']);
            echo "<h2 style=\"text-align: center; \" >" . $urun_title . "</h2>";
            $sql = "SELECT * FROM model WHERE id=$model_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }

            while ($row = $result->fetch_assoc()) {
                $marka_id = $row["marka_id"];
                $model_name = $row["name"];
                $fiyat = $row["fiyat"];
                $isletim_sistemi_id = $row["isletim_sistemi_id"];
                $islemci_tipi_id = $row["islemci_tipi_id"];
                $islemci_nesli_id = $row["islemci_nesli_id"];
                $ssd_id = $row["ssd_id"];
                $hdd_id = $row["hdd_id"];
                $ram_id = $row["ram_id"];
                $ekran_id = $row["ekran_id"];
                $puan_id = $row["puan_id"];
            }
            $sql = "SELECT name FROM marka WHERE id=$marka_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $marka = $row["name"];
            }
            $sql = "SELECT name FROM isletim_sistemi WHERE id=$isletim_sistemi_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $isletim_sistemi = $row["name"];
            }
            $sql = "SELECT name FROM islemci_tipi  WHERE id=$islemci_tipi_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $islemci_tipi = $row["name"];
            }

            $sql = "SELECT name FROM islemci_nesli  WHERE id=$islemci_nesli_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $islemci_nesli = $row["name"];
            }


            $sql = "SELECT name FROM ssd  WHERE id=$ssd_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $ssd = $row["name"];
            }

            $sql = "SELECT name FROM hdd  WHERE id=$hdd_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $hdd = $row["name"];
            }

            $sql = "SELECT name FROM ram  WHERE id=$ram_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $ram = $row["name"];
            }

            $sql = "SELECT name FROM puan  WHERE id=$puan_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $puan = $row["name"];
            }

            $sql = "SELECT name FROM ekran  WHERE id=$ekran_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $ekran = $row["name"];
            }

            $sql = "SELECT name FROM ssd  WHERE id=$ssd_id";
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }
            while ($row = $result->fetch_assoc()) {

                $ssd = $row["name"];
            }

            echo " <tr>
<td>" . $marka . "</td>
<td>" . $model_name . "</td>
<td>" . $fiyat . "</td>
<td>" . $isletim_sistemi . "</td>
<td>" . $islemci_tipi . "</td>
<td> " . $islemci_nesli . "</td>
<td>" . $ssd . "</td>
<td>" . $hdd . "</td>
<td>" . $ram . "</td>
<td>" . $ekran . "</td>
<td>" . $puan . "</td>
</tr>"

            ?>
        </tbody>
    </table>
</body>

</html>