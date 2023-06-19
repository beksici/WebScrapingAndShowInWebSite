<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PCS</title>
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
            margin-left: 30%;
            width: 70%;
            background-color: violet;

        }

        #filtrele {
            position: absolute;
            margin-bottom: 10%;
            margin-right: 0%;
            margin-left: 0%;
            width: 150px;
        }

        h2 {

            background-color: azure;
        }

        section {
            background-color: antiquewhite;
        }

        body {
            background-color: antiquewhite;

        }
    </style>
</head>

<body>

    <h1 style="text-align: center;">Ürün Listesi </h1>
    <nav>
        <a href="anasayfa.php?siralama= order by fiyat desc"><button style="color: coral;" style="background-color: darkviolet;">Azalan Fiyat</button> </a>
        <a href="anasayfa.php?siralama= order by fiyat asc"> <button style="color: blue;">Artan Fiyat</button> </a>
    </nav>
    <?php session_start();
    //$_SESSION['sql_filtre'] = "SELECT * from model";
    $sql_filtrelenmis = $_SESSION['sql_filtre'];
    // echo $sql_filtrelenmis;

    //
    ?>
    <table class="table" id="table" border="1">
        <thead style="text-align: center; ">
            <tr>
                <th>Resim</th>
                <th>Ürün Başlığı</th>
                <th>Site</th>
            </tr>
        </thead>
        <tbody>
            <?php

            $siralama = htmlspecialchars($_GET['siralama']);
            $_SESSION['siralama']  = $siralama;
            $servername = "localhost";
            $username = "root";
            $password = "Mysql123";
            $database = "pcdb1";
            $connection = new mysqli($servername, $username, $password, $database);
            if ($connection->connect_error) {
                die("connection Failed" . $connection->connect_error);
            }

            $sql = $sql_filtrelenmis . $siralama;
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
                    <td>  <a href=\"urun_ozellikleri.php?model_id=$modelid&urun_title=$urun_title\" style=\"color: black;\">" . $urun_title . " </a>    </td>
                    <td style=\"background-color: darkslateblue;\" > <a href=\"" . $urun_url . "\"> <img src=\"" . $site_img . "\" alt=\"" . $site . "\" width=\"50px\"> </a> </td>
                 

                </tr>";
                }
            }


            /*
            $sql_marka = "SELECT name FROM marka WHERE id = (SELECT marka_id FROM model WHERE id = $modelid)
            ";
            $resultt = $connection->query($sql_marka);

            while ($roww = $resultt->fetch_assoc()) {
            echo " marka = " . $roww["name"];
            }*/


            ?>



        </tbody>
    </table>



    <section>
        <form action="filtrelenmis.php" method="POST">
            <?php
            session_start();
            $array_ram = array();
            $array_marka = array();
            $array_isletim_sistemi = array();
            $array_ssd = array();
            $array_hdd = array();
            $array_ekran = array();
            $array_puan = array();
            $array_site = array();
            $array_islemci_tipi = array();
            $array_islemci_nesli = array();

            $servername = "localhost";
            $username = "root";
            $password = "Mysql123";
            $database = "pcdb1";
            $connection = new mysqli($servername, $username, $password, $database);
            if ($connection->connect_error) {
                die("connection Failed" . $connection->connect_error);
            }

            $sql = "SELECT count(*) as sayi FROM ram ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $ram_adet = $row["sayi"];
            }
            $_SESSION['ram_adet'] = $ram_adet;
            ?>

            <div>
                <h3> RAM</h3>
                <?php
                for ($i = 1; $i <= $ram_adet; $i++) {
                    $sql = "SELECT name FROM ram where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $ram_name = $row["name"];
                        $array_ram[($i - 1)] = $ram_name;

                        echo   "<label> <input type=\"checkbox\" name=\"r" . $ram_name . "\">" . $ram_name . " </label> <br>";
                    }
                }
                $_SESSION['array_ram'] = $array_ram;
                ?>
            </div>
            <?php
            $sql = "SELECT count(*) as sayi FROM marka ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $marka_adet = $row["sayi"];
            }

            $_SESSION['marka_adet'] = $marka_adet;
            ?>

            <div>
                <h3> MARKA</h3>
                <?php
                for ($i = 1; $i <= $marka_adet; $i++) {
                    $sql = "SELECT name FROM marka where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $marka_name = $row["name"];
                        $array_marka[($i - 1)] = $marka_name;


                        echo   "<label> <input type=\"checkbox\" name=\"" . $marka_name . "\">" . $marka_name . " </label> <br>";
                    }
                }

                $_SESSION['array_marka'] = $array_marka;
                ?>
            </div>


            <?php
            $sql = "SELECT count(*) as sayi FROM isletim_sistemi ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $isletim_sistemi_adet = $row["sayi"];
            }

            $_SESSION['isletim_sistemi_adet'] = $isletim_sistemi_adet;
            ?>

            <div>
                <h3> İŞLETİM SİSTEMİ</h3>
                <?php
                for ($i = 1; $i <= $isletim_sistemi_adet; $i++) {
                    $sql = "SELECT name FROM isletim_sistemi where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $isletim_sistemi_name = $row["name"];
                        $array_isletim_sistemi[($i - 1)] = $isletim_sistemi_name;

                        echo   "<label> <input type=\"checkbox\" name=\"" . $isletim_sistemi_name . "\">" . $isletim_sistemi_name . " </label> <br>";
                    }
                }
                $_SESSION['array_isletim_sistemi'] = $array_isletim_sistemi;
                ?>
            </div>


            <?php
            $sql = "SELECT count(*) as sayi FROM ssd ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $ssd_adet = $row["sayi"];
            }
            $_SESSION['ssd_adet'] = $ssd_adet;
            ?>

            <div>
                <h3> SSD</h3>
                <?php
                for ($i = 1; $i <= $ssd_adet; $i++) {
                    $sql = "SELECT name FROM ssd where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $ssd_name = $row["name"];
                        $array_ssd[($i - 1)] = $ssd_name;


                        echo   "<label> <input type=\"checkbox\" name=\"s" . $ssd_name . "\">" . $ssd_name . " </label> <br>";
                    }
                }
                $_SESSION['array_ssd'] = $array_ssd;
                ?>
            </div>



            <?php
            $sql = "SELECT count(*) as sayi FROM hdd ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $hdd_adet = $row["sayi"];
            }
            $_SESSION['hdd_adet'] = $hdd_adet;
            ?>

            <div>
                <h3> HDD</h3>
                <?php
                for ($i = 1; $i <= $hdd_adet; $i++) {
                    $sql = "SELECT name FROM hdd where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $hdd_name = $row["name"];
                        $array_hdd[($i - 1)] = $hdd_name;

                        echo   "<label> <input type=\"checkbox\" name=\"h" . $hdd_name . "\">" . $hdd_name . " </label> <br>";
                    }
                }
                $_SESSION['array_hdd'] = $array_hdd;
                ?>
            </div>

            <?php
            $sql = "SELECT count(*) as sayi FROM ekran ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $ekran_adet = $row["sayi"];
            }
            $_SESSION['ekran_adet'] = $ekran_adet;
            ?>

            <div>
                <h3> EKRAN BOYUTU</h3>
                <?php
                for ($i = 1; $i <= $ekran_adet; $i++) {
                    $sql = "SELECT name FROM ekran where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $ekran_name = $row["name"];
                        $array_ekran[($i - 1)] = $ekran_name;

                        echo   "<label> <input type=\"checkbox\" name=\"e" . $ekran_name . "\">" . $ekran_name . " </label> <br>";
                    }
                }
                $_SESSION['array_ekran'] = $array_ekran;
                ?>
            </div>

            <?php
            $sql = "SELECT count(*) as sayi FROM puan ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $puan_adet = $row["sayi"];
            }
            $_SESSION['puan_adet'] = $puan_adet;
            ?>

            <div>
                <h3> PUAN</h3>
                <?php
                for ($i = 1; $i <= $puan_adet; $i++) {
                    $sql = "SELECT name FROM puan where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $puan_name = $row["name"];
                        $array_puan[($i - 1)] = $puan_name;

                        echo   "<label> <input type=\"checkbox\" name=\"p" . $puan_name . "\">" . $puan_name . " </label> <br>";
                    }
                }

                $_SESSION['array_puan'] = $array_puan;
                ?>
            </div>



            <?php
            $sql = "SELECT count(*) as sayi FROM site ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $site_adet = $row["sayi"];
            }
            $_SESSION['site_adet'] = $site_adet;
            ?>

            <div>
                <h3> Site</h3>
                <?php
                for ($i = 1; $i <= $site_adet; $i++) {
                    $sql = "SELECT name FROM site where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $site_name = $row["name"];
                        $array_site[($i - 1)] = $site_name;

                        echo   "<label> <input type=\"checkbox\" name=\"" . $site_name . "\">" . $site_name . " </label> <br>";
                    }
                }
                $_SESSION['array_site'] = $array_site;
                ?>
            </div>


            <?php
            $sql = "SELECT count(*) as sayi FROM islemci_tipi ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $islemci_tipi_adet = $row["sayi"];
            }
            $_SESSION['islemci_tipi_adet'] = $islemci_tipi_adet;
            ?>

            <div>
                <h3> İŞLEMCİ </h3>
                <?php
                for ($i = 1; $i <= $islemci_tipi_adet; $i++) {
                    $sql = "SELECT name FROM islemci_tipi where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $islemci_tipi_name = $row["name"];
                        $array_islemci_tipi[($i - 1)] = $islemci_tipi_name;

                        echo   "<label> <input type=\"checkbox\" name=\"t" . $islemci_tipi_name . "\">" . $islemci_tipi_name . " </label> <br>";
                    }
                }
                $_SESSION['array_islemci_tipi'] = $array_islemci_tipi;
                ?>
            </div>



            <?php
            $sql = "SELECT count(*) as sayi FROM islemci_nesli ";
            $result = $connection->query($sql);
            if (!$result) {
                die("Invalid Query : " . $connection->error);
            }


            while ($row = $result->fetch_assoc()) {
                $islemci_nesli_adet = $row["sayi"];
            }
            $_SESSION['islemci_nesli_adet'] = $islemci_nesli_adet;
            ?>

            <div>
                <h3> İŞLEMCİ NESLİ </h3>
                <?php
                for ($i = 1; $i <= $islemci_nesli_adet; $i++) {
                    $sql = "SELECT name FROM islemci_nesli where id=$i ";
                    $result = $connection->query($sql);
                    if (!$result) {
                        die("Invalid Query : " . $connection->error);
                    }



                    while ($row = $result->fetch_assoc()) {
                        $islemci_nesli_name = $row["name"];
                        $array_islemci_nesli[($i - 1)] = $islemci_nesli_name;

                        echo   "<label> <input type=\"checkbox\" name=\"n" . $islemci_nesli_name . "\">" . $islemci_nesli_name . " </label> <br>";
                    }
                }
                $_SESSION['array_islemci_nesli'] = $array_islemci_nesli;
                ?>
            </div>




            <div>
                <h3> FİYAT </h3>
                <?php


                echo   "<label> <input type=\"text\" name=\"min\" placeholder=\". ile yazınız(min)\">min </label> <br>
        
        <label> <input type=\"text\" name=\"max\"  placeholder=\" . ile yazınız(max)\">max </label> <br>";


                ?>
            </div>
            <?php

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
            // echo $_SESSION['sql_filtre'];
            /*$result = $connection->query($sql_filtre);
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

            <br>
            <br>
            <br>

            <input id="filtrele" type="submit" value="Filtrele" name="submit">

        </form>
    </section>







</body>

</html>