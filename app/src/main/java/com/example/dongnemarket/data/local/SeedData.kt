package com.example.dongnemarket.data.local

object SeedData {
    val products = listOf(
        ProductEntity(
            title = "아이패드 에어 5세대 64GB",
            price = 480000,
            description = "작년에 구매해서 케이스 끼고 사용했어요. 기스 없고 배터리 상태 좋습니다.",
            category = "디지털기기",
            location = "송도동",
            imageUrl = "https://picsum.photos/seed/ipad/400/400"
        ),
        ProductEntity(
            title = "자전거 (로드, 입문용)",
            price = 150000,
            description = "출퇴근용으로 탔어요. 타이어 교체한 지 얼마 안 됐습니다.",
            category = "스포츠",
            location = "연수동",
            imageUrl = "https://picsum.photos/seed/bike/400/400"
        ),
        ProductEntity(
            title = "이케아 책상 140cm",
            price = 40000,
            description = "이사 때문에 급처해요. 직접 가져가셔야 합니다.",
            category = "가구",
            location = "송도동",
            imageUrl = "https://picsum.photos/seed/desk/400/400"
        ),
        ProductEntity(
            title = "닌텐도 스위치 + 게임 3개",
            price = 230000,
            description = "본체, 독, 조이콘 풀구성입니다. 게임 칩 3개 같이 드려요.",
            category = "게임",
            location = "청학동",
            imageUrl = "https://picsum.photos/seed/switch/400/400"
        ),
        ProductEntity(
            title = "전공 서적 일괄",
            price = 20000,
            description = "컴퓨터공학 전공 책 5권입니다. 필기 조금 있어요.",
            category = "도서",
            location = "연수동",
            imageUrl = "https://picsum.photos/seed/books/400/400"
        ),
        ProductEntity(
            title = "무선 청소기",
            price = 90000,
            description = "흡입력 좋고 배터리 1시간 정도 가요.",
            category = "생활가전",
            location = "송도동",
            imageUrl = "https://picsum.photos/seed/vacuum/400/400"
        )
    )
}