package bean;

public class PagingBean {
    int numPerPage = 10; // 페이지당 레코드 수 (고정값)
    int pagePerBlock = 5; //블럭당 페이지수 (고정값)

    int cPage = 1; // 현재페이지
    int cBlock = 1;  //현재블럭

    int totalRecord = 0; //전체 게시물 수

    int totalPage = 0; //전체 페이지 수
    int totalBlock = 0;  //전체 블럭수

    int pageStart = 0; // 하단페이지 시작번호
    int pageEnd = 0; // 하단페이지 끝번호

    int start = 0; // db검색 시작
    int end = 0; // db검색 끝

    // setTotalRecord 에서 호출됨
    public void subPaging(){
        totalPage = (int) Math.ceil((double) totalRecord / numPerPage);  //전체페이지수
        cBlock = (int) Math.ceil((double) cPage / pagePerBlock); //현재블럭 계산
        totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);  //전체블럭계산

        start = (cPage * numPerPage) - numPerPage; // db검색 시작
        end = numPerPage; // db검색 끝

        pageStart = (cBlock - 1) * pagePerBlock + 1; //하단 페이지 시작번호
        pageEnd = ((pageStart + pagePerBlock) <= totalPage) ? (pageStart + pagePerBlock) : totalPage + 1;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPagePerBlock() {
        return pagePerBlock;
    }

    public void setPagePerBlock(int pagePerBlock) {
        this.pagePerBlock = pagePerBlock;
    }

    public int getcPage() {
        return cPage;
    }

    public void setcPage(int cPage) {
        this.cPage = cPage;
    }

    public int getcBlock() {
        return cBlock;
    }

    public void setcBlock(int cBlock) {
        this.cBlock = cBlock;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        this.subPaging();
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalBlock() {
        return totalBlock;
    }

    public void setTotalBlock(int totalBlock) {
        this.totalBlock = totalBlock;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
