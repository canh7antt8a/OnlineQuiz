# OnlineQuiz
Tool thi trắc nghiệm online dành cho server yếu.
1. Tool và thư viện:
  1.1. Tool:
    - Eclipse
    - Apache tomcat 7
  1.2. Thư viện:
    - Thư viện kết nối database jtds 1.3.1
    - Apache POI 3.16
2. Chức năng học viên:
  - Quản lý thông tin cá nhân.
  - Đổi mật khẩu tài khoản.
  - Làm bài thi trắc nghiệm online.
3. Chức năng admin:
  3.1. Admin chính:
    - Quản lý admin con: thêm admin con, đổi mật khẩu admin con, xoá admin con.
    - Đổi mật khẩu admin chính.
    - Quản lý lớp học: thêm lớp, xoá lớp, sữa tên lớp.
    - Quản lý học viên trong lớp: thêm học viên, xoá học viên, sữa thông tin học viên.
    - Quản lý topic thi: thêm topic, xoá topic, sữa tên topic.
    - Lấy kết quả làm bài của học viên: chấm điểm và lấy kết quả học viên theo lớp hoặc topic.
    - Kiểm tra IP (xét học viên có làm bài thi cho học viên khác).
    - Reset IP/Xóa dữ liệu: reset IP khi học viên gặp sự cố theo từng học viên, reset IP của lớp thi song để lớp khác tiếp tục thi, Xoá toàn bộ dữ liệu trong database (trừ admin chính).
  3.2. Admin con:
    - Đổi mật khẩu.
    - Quản lý lớp học: thêm lớp, xoá lớp, sữa tên lớp.
    - Quản lý học viên trong lớp: thêm học viên, xoá học viên, sữa thông tin học viên.
    - Quản lý topic thi: thêm topic, xoá topic, sữa tên topic.
    - Lấy kết quả làm bài của học viên: chấm điểm và lấy kết quả học viên theo lớp hoặc topic.
    - Kiểm tra IP (xét học viên có làm bài thi cho học viên khác).
    - Reset IP: reset IP khi học viên gặp sự cố theo từng học viên, reset IP của lớp thi song để lớp khác tiếp tục thi.
