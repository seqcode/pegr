# Reference & Dictionary

```mermaid
erDiagram
    Definition {
        string name
        string content
    }
    Chores {
        string name
        string value
    }
    History {
        date dateCreated
        int objectId
        string objectType
        string action
        string notes
    }
    User {
        string username
    }
    Project {
        string name
    }

    History }o--|| User : "user"
    History }o--o| Project : "project"
```
