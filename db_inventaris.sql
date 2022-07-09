-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Jun 2022 pada 04.44
-- Versi server: 10.4.18-MariaDB
-- Versi PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_inventaris`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kd_barang` varchar(10) NOT NULL,
  `idsupplier` varchar(15) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `ukuran` varchar(30) NOT NULL,
  `harga` int(12) NOT NULL,
  `stok` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kd_barang`, `idsupplier`, `nama_barang`, `jenis`, `ukuran`, `harga`, `stok`) VALUES
('B005', 'S001', 'jessica pink', 'HighHeels', '37', 120000, 10),
('B006', 'S001', 'irene grey', 'Sandals', '39', 120000, 5),
('B008', 'S001', 'jasmine pich', 'Sandals', '38', 110000, 6),
('B009', 'S001', 'grizzele maroon', 'HighHeels', '37', 120000, 9),
('B011', 'S001', 'nickie black', 'Sandals', '38', 120000, 6),
('B012', 'S001', 'july gold', 'FlatShoes', '39', 115000, 0),
('B013', 'S001', 'july black', 'FlatShoes', '37', 115000, 0),
('B015', 'S001', 'alice pich', 'HighHeels', '37', 120000, 0),
('B016', 'S001', 'alice tosca', 'HighHeels', '38', 120000, 0),
('B017', 'S001', 'karine brown', 'HighHeels', '37', 130000, 2),
('B018', 'S001', 'brianna black', 'HighHeels', '38', 120000, 0),
('B019', 'S001', 'rebeca brown', 'HighHeels', '38', 130000, 5),
('B020', 'S001', 'perie gold', 'HighHeels', '38', 130000, 0),
('B021', 'S001', 'khanza', 'HighHeels', '40', 130000, 0),
('B022', 'S001', 'sepatu ', 'FlatShoes', '38', 110000, 5),
('B023', 'S002', 'adada', 'Sandals', '37', 135000, 6),
('B024', 'S004', 'sepatu', 'FlatShoes', '37', 100000, 7),
('B025', 'S005', 'tang', 'FlatShoes', '14', 13000, 18);

-- --------------------------------------------------------

--
-- Struktur dari tabel `barangmasuk`
--

CREATE TABLE `barangmasuk` (
  `namakaryawan` varchar(30) NOT NULL,
  `kd_masuk` varchar(15) NOT NULL,
  `kd_barang` varchar(15) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `tanggal` date NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `ukuran` int(3) NOT NULL,
  `harga` int(12) NOT NULL,
  `jumlah` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barangmasuk`
--

INSERT INTO `barangmasuk` (`namakaryawan`, `kd_masuk`, `kd_barang`, `nama_barang`, `tanggal`, `jenis`, `ukuran`, `harga`, `jumlah`) VALUES
('Duta Admojo', 'M001', 'B006', 'irene grey', '2020-06-20', 'Sandals', 39, 120000, 5),
('Duta Admojo', 'M002', 'B005', 'jessica pink', '2020-06-20', 'HighHeels', 37, 120000, 5),
('Duta Admojo', 'M003', 'B011', 'nickie black', '2020-06-27', 'Sandals', 38, 120000, 6),
('Duta Admojo', 'M004', 'B019', 'rebeca brown', '2020-06-30', 'HighHeels', 38, 130000, 8),
('Duta Admojo', 'M005', 'B017', 'karine brown', '2020-06-30', 'HighHeels', 37, 130000, 2),
('Anggie Triamsih', 'M006', 'B024', 'sepatu', '2020-07-24', 'FlatShoes', 37, 100000, 5),
('Dina Budi', 'M007', 'B025', 'tang', '2022-05-02', 'FlatShoes', 38, 13000, 10);

--
-- Trigger `barangmasuk`
--
DELIMITER $$
CREATE TRIGGER `ai` AFTER INSERT ON `barangmasuk` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok+new.jumlah WHERE kd_barang=new.kd_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `au` AFTER UPDATE ON `barangmasuk` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok+new.jumlah WHERE kd_barang=new.kd_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `brgkeluar`
--

CREATE TABLE `brgkeluar` (
  `namakaryawan` varchar(20) NOT NULL,
  `kdkeluar` varchar(20) NOT NULL,
  `kd_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(20) NOT NULL,
  `tanggalkeluar` date NOT NULL,
  `jenis` varchar(40) NOT NULL,
  `ukuran` int(5) NOT NULL,
  `harga` int(12) NOT NULL,
  `hargajual` int(12) NOT NULL,
  `jumlahmasuk` int(12) NOT NULL,
  `jumlahkeluar` int(12) NOT NULL,
  `stok` int(12) NOT NULL,
  `laba` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `brgkeluar`
--

INSERT INTO `brgkeluar` (`namakaryawan`, `kdkeluar`, `kd_barang`, `nama_barang`, `tanggalkeluar`, `jenis`, `ukuran`, `harga`, `hargajual`, `jumlahmasuk`, `jumlahkeluar`, `stok`, `laba`) VALUES
('bagas', 'K001', 'B005', 'jessica pink', '2020-06-19', 'HighHeels', 37, 120000, 130000, 20, 10, 10, 100000),
('Dina Budi', 'K002', 'B019', 'rebeca brown', '2020-06-30', 'HighHeels', 38, 130000, 150000, 8, 3, 5, 60000),
('Anggie Triamsih', 'K003', 'B024', 'sepatu', '2020-07-24', 'FlatShoes', 37, 100000, 130000, 10, 3, 7, 90000),
('Dina Budi', 'K004', 'B025', 'tang', '2022-05-02', 'FlatShoes', 37, 13000, 14000, 20, 2, 18, 2000);

--
-- Trigger `brgkeluar`
--
DELIMITER $$
CREATE TRIGGER `a` AFTER INSERT ON `brgkeluar` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-new.jumlahkeluar WHERE kd_barang=new.kd_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `bismillah` AFTER UPDATE ON `brgkeluar` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-new.jumlahkeluar WHERE kd_barang=new.kd_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `id` varchar(8) NOT NULL,
  `noktp` varchar(20) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(15) NOT NULL,
  `notelp` varchar(13) NOT NULL,
  `jabatan` varchar(20) NOT NULL,
  `keterangan` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`id`, `noktp`, `nama`, `alamat`, `jenis_kelamin`, `notelp`, `jabatan`, `keterangan`, `username`, `password`) VALUES
('P0004', '338172180008', 'Dina Budi', 'pesona laguna		', 'Perempuan', '084848', 'admin', 'Admin', 'admin', 'admin'),
('P0005', '8777666', 'endah p', 'kp curug', 'Perempuan', '0857820834', 'admin', 'Admin', 'endah', 'endah'),
('P0003', '6666', 'Anggie Triamsih', 'jln suka suka', 'Perempuan', '899999', 'owner', 'Owner', 'owner', 'owner');

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `idsupplier` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tlp` varchar(15) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`idsupplier`, `nama`, `tlp`, `alamat`) VALUES
('S001', 'A.B.E Shoses', '081288747154', 'pasar kebon kembang, lantai 1 blok b2 no 139, kota bogor, jawa barat'),
('S002', 'fun shoes', '081584824464', 'kp.parung jambu, kota bogor'),
('S003', 'dddd', '0889898', 'jalaan'),
('S004', 'melky', '087873731', 'cilangkap'),
('S005', 'Anugera', '0812121', 'jatiasih');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kd_barang`);

--
-- Indeks untuk tabel `barangmasuk`
--
ALTER TABLE `barangmasuk`
  ADD PRIMARY KEY (`kd_masuk`),
  ADD KEY `kd_barang` (`kd_barang`);

--
-- Indeks untuk tabel `brgkeluar`
--
ALTER TABLE `brgkeluar`
  ADD PRIMARY KEY (`kdkeluar`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`idsupplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
