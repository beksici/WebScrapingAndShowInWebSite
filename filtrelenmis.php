<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FİLTRELENMİŞ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
        thead {
            background-color: darkgray;
        }

        h1 {
            background-color: lightblue;
        }

        .table {
            position: absolute;
            margin-left: 0%;
            width: 100%;
            background-color: violet;

        }

        iframe {
            position: absolute;
            height: 100%;

        }
    </style>
</head>

<body>

    <?php
    $servername = "localhost";
    $username = "root";
    $password = "Mysql123";
    $database = "pcdb1";
    $connection = new mysqli($servername, $username, $password, $database);
    if ($connection->connect_error) {
        die("connection Failed" . $connection->connect_error);
    }
    session_start();
    $ram_adet = $_SESSION['ram_adet'];

    $array_ram = $_SESSION['array_ram'];
    $marka_adet = $_SESSION['marka_adet'];
    $array_marka = $_SESSION['array_marka'];

    $isletim_sistemi_adet = $_SESSION['isletim_sistemi_adet'];
    $array_isletim_sistemi = $_SESSION['array_isletim_sistemi'];
    $ssd_adet = $_SESSION['ssd_adet'];
    $array_ssd = $_SESSION['array_ssd'];
    $hdd_adet =  $_SESSION['hdd_adet'];
    $array_hdd = $_SESSION['array_hdd'];
    $ekran_adet = $_SESSION['ekran_adet'];
    $array_ekran = $_SESSION['array_ekran'];
    $puan_adet = $_SESSION['puan_adet'];
    $array_puan = $_SESSION['array_puan'];
    $site_adet = $_SESSION['site_adet'];
    $array_site = $_SESSION['array_site'];


    $islemci_tipi_adet = $_SESSION['islemci_tipi_adet'];
    $array_islemci_tipi = $_SESSION['array_islemci_tipi'];
    $islemci_nesli_adet = $_SESSION['islemci_nesli_adet'];
    $array_islemci_nesli = $_SESSION['array_islemci_nesli'];

    if (!empty($_POST["min"])) {
        $min_fiyat = $_POST["min"];
    } else $min_fiyat = "0";
    if (!empty($_POST["max"])) {
        $max_fiyat = $_POST["max"];
    } else $max_fiyat = "200.000";





    $ram_ids = array();
    for ($j = 0; $j < $ram_adet; $j++) {
        if ($_POST["r" . $array_ram[$j]] == "on") {
            array_push($ram_ids, ($j + 1));
        }
    }
    $sorgu_ram = "in(";
    if (!empty($ram_ids)) {
        for ($k = 0; $k < sizeof($ram_ids); $k++) {


            if ($k == (sizeof($ram_ids) - 1) || sizeof($ram_ids) == 1) {
                $sorgu_ram .= $ram_ids[$k] . ")";
            } else
                $sorgu_ram .= $ram_ids[$k] . ",";
        }
    } else $sorgu_ram = ""; //tüm ramları getirtmem lazım


    $marka_ids = array();
    for ($j = 0; $j < $marka_adet; $j++) {
        if ($_POST["" . $array_marka[$j]] == "on") {
            array_push($marka_ids, ($j + 1));
        }
    }
    $sorgu_marka = "in(";
    if (!empty($marka_ids)) {
        for ($k = 0; $k < sizeof($marka_ids); $k++) {


            if ($k == (sizeof($marka_ids) - 1) || sizeof($marka_ids) == 1) {
                $sorgu_marka .= $marka_ids[$k] . ")";
            } else
                $sorgu_marka .= $marka_ids[$k] . ",";
        }
    } else $sorgu_marka = "";


    $isletim_sistemi_ids = array();
    for ($j = 0; $j < $isletim_sistemi_adet; $j++) {
        if ($_POST["" . $array_isletim_sistemi[$j]] == "on") {
            array_push($isletim_sistemi_ids, ($j + 1));
        }
    }
    $sorgu_isletim_sistemi = "in(";
    if (!empty($isletim_sistemi_ids)) {
        for ($k = 0; $k < sizeof($isletim_sistemi_ids); $k++) {


            if ($k == (sizeof($isletim_sistemi_ids) - 1) || sizeof($isletim_sistemi_ids) == 1) {
                $sorgu_isletim_sistemi .= $isletim_sistemi_ids[$k] . ")";
            } else
                $sorgu_isletim_sistemi .= $isletim_sistemi_ids[$k] . ",";
        }
    } else $sorgu_isletim_sistemi = "";




    $ssd_ids = array();
    for ($j = 0; $j < $ssd_adet; $j++) {
        if ($_POST["s" . $array_ssd[$j]] == "on") {
            array_push($ssd_ids, ($j + 1));
        }
    }
    $sorgu_ssd = "in(";
    if (!empty($ssd_ids)) {
        for ($k = 0; $k < sizeof($ssd_ids); $k++) {


            if ($k == (sizeof($ssd_ids) - 1) || sizeof($ssd_ids) == 1) {
                $sorgu_ssd .= $ssd_ids[$k] . ")";
            } else
                $sorgu_ssd .= $ssd_ids[$k] . ",";
        }
    } else $sorgu_ssd = "";


    $hdd_ids = array();
    for ($j = 0; $j < $hdd_adet; $j++) {
        if ($_POST["h" . $array_hdd[$j]] == "on") {
            array_push($hdd_ids, ($j + 1));
        }
    }
    $sorgu_hdd = "in(";
    if (!empty($hdd_ids)) {
        for ($k = 0; $k < sizeof($hdd_ids); $k++) {


            if ($k == (sizeof($hdd_ids) - 1) || sizeof($hdd_ids) == 1) {
                $sorgu_hdd .= $hdd_ids[$k] . ")";
            } else
                $sorgu_hdd .= $hdd_ids[$k] . ",";
        }
    } else $sorgu_hdd = "";




    $ekran_ids = array();
    for ($j = 0; $j < $ekran_adet; $j++) {
        if ($_POST["e" . $array_ekran[$j]] == "on") {
            array_push($ekran_ids, ($j + 1));
        }
    }
    $sorgu_ekran = "in(";
    if (!empty($ekran_ids)) {
        for ($k = 0; $k < sizeof($ekran_ids); $k++) {


            if ($k == (sizeof($ekran_ids) - 1) || sizeof($ekran_ids) == 1) {
                $sorgu_ekran .= $ekran_ids[$k] . ")";
            } else
                $sorgu_ekran .= $ekran_ids[$k] . ",";
        }
    } else $sorgu_ekran = "";



    $puan_ids = array();
    for ($j = 0; $j < $puan_adet; $j++) {
        if ($_POST["p" . $array_puan[$j]] == "on") {
            array_push($puan_ids, ($j + 1));
        }
    }
    $sorgu_puan = "in(";
    if (!empty($puan_ids)) {
        for ($k = 0; $k < sizeof($puan_ids); $k++) {


            if ($k == (sizeof($puan_ids) - 1) || sizeof($puan_ids) == 1) {
                $sorgu_puan .= $puan_ids[$k] . ")";
            } else
                $sorgu_puan .= $puan_ids[$k] . ",";
        }
    } else $sorgu_puan = "";


    $site_ids = array();
    for ($j = 0; $j < $site_adet; $j++) {
        if ($_POST["" . $array_site[$j]] == "on") {
            array_push($site_ids, ($j + 1));
        }
    }
    $sorgu_site = "in(";
    if (!empty($site_ids)) {
        for ($k = 0; $k < sizeof($site_ids); $k++) {


            if ($k == (sizeof($site_ids) - 1) || sizeof($site_ids) == 1) {
                $sorgu_site .= $site_ids[$k] . ")";
            } else
                $sorgu_site .= $site_ids[$k] . ",";
        }
    } else $sorgu_site = "";




    $islemci_tipi_ids = array();
    for ($j = 0; $j < $islemci_tipi_adet; $j++) {
        if ($_POST["t" . $array_islemci_tipi[$j]] == "on") {
            array_push($islemci_tipi_ids, ($j + 1));
        }
    }
    $sorgu_islemci_tipi = "in(";
    if (!empty($islemci_tipi_ids)) {
        for ($k = 0; $k < sizeof($islemci_tipi_ids); $k++) {


            if ($k == (sizeof($islemci_tipi_ids) - 1) || sizeof($islemci_tipi_ids) == 1) {
                $sorgu_islemci_tipi .= $islemci_tipi_ids[$k] . ")";
            } else
                $sorgu_islemci_tipi .= $islemci_tipi_ids[$k] . ",";
        }
    } else $sorgu_islemci_tipi = "";


    $islemci_nesli_ids = array();
    for ($j = 0; $j < $islemci_nesli_adet; $j++) {
        if ($_POST["n" . $array_islemci_nesli[$j]] == "on") {
            array_push($islemci_nesli_ids, ($j + 1));
        }
    }
    $sorgu_islemci_nesli = "in(";
    if (!empty($islemci_nesli_ids)) {
        for ($k = 0; $k < sizeof($islemci_nesli_ids); $k++) {


            if ($k == (sizeof($islemci_nesli_ids) - 1) || sizeof($islemci_nesli_ids) == 1) {
                $sorgu_islemci_nesli .= $islemci_nesli_ids[$k] . ")";
            } else
                $sorgu_islemci_nesli .= $islemci_nesli_ids[$k] . ",";
        }
    } else $sorgu_islemci_nesli = "";


    $sql_filtre_site = "SELECT model_id as id From model_site where site_id " . $sorgu_site . "";
    $sorgu_sitee = array();
    $result = $connection->query($sql_filtre_site);
    if (!$result) {
        die("Invalid Query : " . $connection->error);
    }


    while ($row = $result->fetch_assoc()) {
        array_push($sorgu_sitee, $row["id"]);
    }

    $sorgu_site_model = "in(";
    if (!empty($sorgu_sitee)) {
        for ($k = 0; $k < sizeof($sorgu_sitee); $k++) {


            if ($k == (sizeof($sorgu_sitee) - 1) || sizeof($sorgu_sitee) == 1) {
                $sorgu_site_model .= $sorgu_sitee[$k] . ")";
            } else
                $sorgu_site_model .= $sorgu_sitee[$k] . ",";
        }
    } else $sorgu_site_model = "";



    $sql_filtre = "SELECT id FROM model where ram_id " . $sorgu_ram . " and marka_id " . $sorgu_marka . " and isletim_sistemi_id " . $sorgu_isletim_sistemi . " and ssd_id " . $sorgu_ssd . " and hdd_id " . $sorgu_hdd . " and ekran_id " . $sorgu_ekran . " and puan_id " . $sorgu_puan . " and islemci_tipi_id " . $sorgu_islemci_tipi . " and islemci_nesli_id " . $sorgu_islemci_nesli . " and fiyat>" . $min_fiyat . " and fiyat<" . $max_fiyat . " and id " . $sorgu_site_model . " ";
    $_SESSION['sql_filtre'] = $sql_filtre;
    /* echo $_SESSION['sql_filtre'];
    $result = $connection->query($sql_filtre);
    if (!$result) {
        die("Invalid Query : " . $connection->error);
    }


    while ($row = $result->fetch_assoc()) {
        if (!empty($row))
            echo $row["id"];
        else
            echo " sql boş";
    }

*/







    ?>













    <h1 style="text-align: center;"> Filtrelenmiş Ürün Listesi </h1>

    <?php session_start();
    $sql_filtrelenmis = $_SESSION['sql_filtre']; ?>
    <table class="table" border="1">
        <thead style="text-align: center; ">
            <tr>
                <th>Resim</th>
                <th>Ürün Başlığı</th>
                <th>Site</th>
            </tr>
        </thead>
        <tbody>
            <?php


            $siralama = $_SESSION['siralama'];
            // echo $siralama;
            $sql = $_SESSION['sql_filtre'] . $siralama;
            //  echo $sql;
            $result = $connection->query($sql);

            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {

                $modelid = $row["id"];
                $sorgu  = "SELECT * FROM model where id =" . $modelid;
                $resultt = $connection->query($sorgu);


                if (!$resultt) {
                    die("Invalid Query : " . $connection->error);
                }
                while ($roww = $resultt->fetch_assoc()) {

                    $img_url = $roww["image_url"];
                    $urun_url = $roww["urun_url"];
                    $urun_title = $roww["urun_title"];
                    $sql_site = "SELECT name FROM site WHERE id= (SELECT site_id FROM model_site WHERE model_id = ($modelid))";
                    $resulttt = $connection->query($sql_site);

                    while ($rowww = $resulttt->fetch_assoc()) {
                        $site =   $rowww["name"];
                    }
                    if ($site == "Vatan")
                        $site_img = "vatan.png";
                    else if ($site == "Trendyol")
                        $site_img = "trendyol.png";
                    else if ($site == "N11")
                        $site_img = "n11.png";
                    else  $site_img = "teknosa.png";
                    echo
                    "<tr>
                    <td> <img src= \"" . $img_url . "\" alt=\"image_url\" width=\"150 \"> </td>
                    <td>  <a href=\"urun_ozellikleri.php?model_id=$modelid&urun_title=$urun_title\" target=\"_blank\" style=\"color: black;\">" . $urun_title .  " </a>    </td>
                    <td style=\"background-color: darkslateblue;\"> <a href=\"" . $urun_url . "\"> <img src=\"" . $site_img . "\" alt=\"" . $site . "\" width=\"50px\"> </a> </td>
                 

                </tr>";
                }
            }

            ?>



        </tbody>
    </table>




</body>

</html>