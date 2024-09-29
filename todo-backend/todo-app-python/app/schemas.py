from pydantic import BaseModel


class TodoBase(BaseModel):
    task: str
    completed: bool = False


class TodoCreate(TodoBase):
    pass


class TodoUpdate(TodoBase):
    completed: bool


class Todo(TodoBase):
    id: int

    class Config:
        orm_mode = True
