# CH3 일정 관리 앱

Spring Boot와 JPA를 활용하여 일정 생성, 조회, 수정, 삭제 기능을 구현하는 프로젝트입니다.

## API 명세

### 공통 사항
- 모든 요청 및 응답 데이터 형식은 **JSON**입니다.
- 일정 조회 응답에는 **비밀번호(password)를 포함하지 않습니다.**
- 작성일(`createdAt`), 수정일(`updatedAt`)은 **날짜 + 시간**을 포함합니다.
- 전체 일정 조회는 **수정일(`updatedAt`) 기준 내림차순**으로 정렬합니다.

---

## API 요약

| 기능 | Method | URL | 설명 |
|---|---|---|---|
| 일정 생성 | POST | `/schedules` | 새로운 일정을 생성합니다. |
| 전체 일정 조회 | GET | `/schedules` | 전체 일정을 조회합니다. |
| 전체 일정 조회(작성자명 조건) | GET | `/schedules?writerName={writerName}` | 작성자명으로 일정을 조회합니다. |
| 선택 일정 조회 | GET | `/schedules/{scheduleId}` | 선택한 일정 1건을 조회합니다. |
| 일정 수정 | PUT | `/schedules/{scheduleId}` | 일정 제목, 작성자명을 수정합니다. |
| 일정 삭제 | DELETE | `/schedules/{scheduleId}` | 선택한 일정을 삭제합니다. |

---

## 1. 일정 생성

- **Method**: `POST`
- **URL**: `/schedules`
- **설명**: 새로운 일정을 생성합니다.

### Request Body
```json
{
  "title": "운동 가기",
  "content": "저녁 7시에 헬스장 가기",
  "writerName": "imsungu",
  "password": "1234"
}
```

### Response Body
```json
{
  "id": 1,
  "title": "운동 가기",
  "content": "저녁 7시에 헬스장 가기",
  "writerName": "imsungu",
  "createdAt": "2026-04-10T20:00:00",
  "updatedAt": "2026-04-10T20:00:00"
}
```

### 성공 상태 코드
- `201 Created`

---

## 2. 전체 일정 조회

- **Method**: `GET`
- **URL**: `/schedules`
- **설명**: 전체 일정을 조회합니다.

### Query Parameter

| 이름 | 타입 | 필수 여부 | 설명 |
|---|---|---|---|
| writerName | String | 선택 | 작성자명으로 조회할 때 사용 |

### 요청 예시
```text
GET /schedules
GET /schedules?writerName=imsungu
```

### Response Body
```json
[
  {
    "id": 2,
    "title": "JPA 과제하기",
    "content": "README와 ERD 작성",
    "writerName": "imsungu",
    "createdAt": "2026-04-10T18:00:00",
    "updatedAt": "2026-04-10T19:30:00"
  },
  {
    "id": 1,
    "title": "운동 가기",
    "content": "저녁 7시에 헬스장 가기",
    "writerName": "imsungu",
    "createdAt": "2026-04-10T17:00:00",
    "updatedAt": "2026-04-10T18:00:00"
  }
]
```

### 성공 상태 코드
- `200 OK`

---

## 3. 선택 일정 조회

- **Method**: `GET`
- **URL**: `/schedules/{scheduleId}`
- **설명**: 선택한 일정 1건을 조회합니다.

### Path Variable

| 이름 | 타입 | 설명 |
|---|---|---|
| scheduleId | Long | 조회할 일정의 고유 식별자(ID) |

### 요청 예시
```text
GET /schedules/1
```

### Response Body
```json
{
  "id": 1,
  "title": "운동 가기",
  "content": "저녁 7시에 헬스장 가기",
  "writerName": "imsungu",
  "createdAt": "2026-04-10T20:00:00",
  "updatedAt": "2026-04-10T20:00:00"
}
```

### 성공 상태 코드
- `200 OK`

---

## 4. 일정 수정

- **Method**: `PUT`
- **URL**: `/schedules/{scheduleId}`
- **설명**: 선택한 일정의 **일정 제목, 작성자명**만 수정합니다.  
  일정 수정 요청 시 비밀번호를 함께 전달해야 합니다.

### Path Variable

| 이름 | 타입 | 설명 |
|---|---|---|
| scheduleId | Long | 수정할 일정의 고유 식별자(ID) |

### Request Body
```json
{
  "title": "운동 가기 수정",
  "writerName": "imsungu2",
  "password": "1234"
}
```

### Response Body
```json
{
  "id": 1,
  "title": "운동 가기 수정",
  "content": "저녁 7시에 헬스장 가기",
  "writerName": "imsungu2",
  "createdAt": "2026-04-10T20:00:00",
  "updatedAt": "2026-04-10T21:00:00"
}
```

### 성공 상태 코드
- `200 OK`

---

## 5. 일정 삭제

- **Method**: `DELETE`
- **URL**: `/schedules/{scheduleId}`
- **설명**: 선택한 일정을 삭제합니다.  
  일정 삭제 요청 시 비밀번호를 함께 전달해야 합니다.

### Path Variable

| 이름 | 타입 | 설명 |
|---|---|---|
| scheduleId | Long | 삭제할 일정의 고유 식별자(ID) |

### Request Body
```json
{
  "password": "1234"
}
```

### Response Body
```json
{
  "message": "일정 삭제 완료"
}
```

### 성공 상태 코드
- `200 OK`

---

## 예외 응답 예시

### 비밀번호가 일치하지 않는 경우
```json
{
  "message": "비밀번호가 일치하지 않습니다."
}
```

### 존재하지 않는 일정을 조회/수정/삭제하는 경우
```json
{
  "message": "해당 일정을 찾을 수 없습니다."
}
```

---

## ERD

### Schedule

| 컬럼명 | 타입 | Null 허용 여부 | 설명 |
|---|---|---|---|
| id | bigint | 불가 | 일정 고유 식별자 (PK, 자동 생성) |
| title | varchar(200) | 불가 | 일정 제목 |
| content | text | 불가 | 일정 내용 |
| writer_name | varchar(100) | 불가 | 작성자명 |
| password | varchar(100) | 불가 | 일정 수정/삭제용 비밀번호 |
| created_at | datetime | 불가 | 일정 생성일 |
| updated_at | datetime | 불가 | 일정 수정일 |

### 테이블 설명
- 이번 과제는 **일정(Schedule) 엔티티 1개**로 구성된 단일 테이블 구조입니다.
- `id`는 기본 키(PK)이며 자동 생성됩니다.
- `created_at`, `updated_at`는 **JPA Auditing**을 사용하여 자동 저장합니다.
- `password`는 일정 수정 및 삭제 시 확인용으로 사용합니다.
- 일정 조회 시에는 **password를 응답에 포함하지 않습니다.**

### ERD 구조
```text
Schedule
├── id : bigint (PK)
├── title : varchar(200)
├── content : text
├── writer_name : varchar(100)
├── password : varchar(100)
├── created_at : datetime
└── updated_at : datetime
```