package Entity;

import java.util.Date;

public class PhieuGiaoHang_ChiTiet {

    private int maHoaDon;
    private String tenAoKhoac;
    private int soLuongHDCT;
    private float donGia;
    private String DVVC;
    private boolean hinhThucThanhToan;
    private int phiVanChuyen;
    private String tenMauSac;
    private Date ngayGiao;
    private Date ngayNhan;
    public PhieuGiaoHang_ChiTiet() {
    }

    public PhieuGiaoHang_ChiTiet(int maHoaDon, String tenAoKhoac, int soLuongHDCT, float donGia, String DVVC, boolean hinhThucThanhToan, int phiVanChuyen, String tenMauSac, Date ngayGiao, Date ngayNhan) {
        this.maHoaDon = maHoaDon;
        this.tenAoKhoac = tenAoKhoac;
        this.soLuongHDCT = soLuongHDCT;
        this.donGia = donGia;
        this.DVVC = DVVC;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.phiVanChuyen = phiVanChuyen;
        this.tenMauSac = tenMauSac;
        this.ngayGiao = ngayGiao;
        this.ngayNhan = ngayNhan;
    }

   

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenAoKhoac() {
        return tenAoKhoac;
    }

    public void setTenAoKhoac(String tenAoKhoac) {
        this.tenAoKhoac = tenAoKhoac;
    }

    public int getSoLuongHDCT() {
        return soLuongHDCT;
    }

    public void setSoLuongHDCT(int soLuongHDCT) {
        this.soLuongHDCT = soLuongHDCT;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public String getDVVC() {
        return DVVC;
    }

    public void setDVVC(String DVVC) {
        this.DVVC = DVVC;
    }

    public boolean isHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(boolean hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public int getPhiVanChuyen() {
        return phiVanChuyen;
    }

    public void setPhiVanChuyen(int phiVanChuyen) {
        this.phiVanChuyen = phiVanChuyen;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    @Override
    public String toString() {
        return "PhieuGiaoHang_ChiTiet{" + "maHoaDon=" + maHoaDon + ", tenAoKhoac=" + tenAoKhoac + ", soLuongHDCT=" + soLuongHDCT + ", donGia=" + donGia + ", DVVC=" + DVVC + ", hinhThucThanhToan=" + hinhThucThanhToan + ", phiVanChuyen=" + phiVanChuyen + ", tenMauSac=" + tenMauSac + '}';
    }

}
