import pytest
from fastapi.testclient import TestClient
from app.main import app
from app.database import get_db, get_test_db, Base, test_engine

client = TestClient(app)


@pytest.fixture(autouse=True)
def create_test_db():
    Base.metadata.create_all(bind=test_engine)
    yield
    Base.metadata.drop_all(bind=test_engine)


app.dependency_overrides[get_db] = get_test_db


def test_create_todo():
    todo_data = {
        "task": "Test Todo",
        "completed": False
    }
    response = client.post("/todos/", json=todo_data)
    assert response.status_code == 200
    data = response.json()
    assert data["task"] == todo_data["task"]


def test_read_todos():
    todo_data = {
        "task": "Test Todo",
        "completed": False
    }
    client.post("/todos/", json=todo_data)

    response = client.get("/todos/")
    assert response.status_code == 200
    data = response.json()
    assert len(data) == 1
    assert data[0]["task"] == todo_data["task"]


def test_update_todo():
    todo_data = {
        "task": "Test Todo",
        "completed": False
    }
    response = client.post("/todos/", json=todo_data)
    todo_id = response.json()["id"]

    # Update the created todo
    update_data = {
        "task": "Updated Title",
        "completed": True
    }
    response = client.put(f"/todos/{todo_id}", json=update_data)
    assert response.status_code == 200
    updated_todo = response.json()
    assert updated_todo["task"] == "Updated Title"
    assert updated_todo["completed"] == True


def test_delete_todo():
    # Create a to-do for testing
    todo_data = {
        "task": "Test Todo",
        "completed": False
    }
    response = client.post("/todos/", json=todo_data)
    todo_id = response.json()["id"]

    # Delete the created todo
    response = client.delete(f"/todos/{todo_id}")
    assert response.status_code == 200
    assert response.json() == {"detail": "Todo deleted"}

    # Check that the to-do no longer exists
    response = client.get(f"/todos/{todo_id}")
    assert response.status_code == 404
    assert response.json() == {"detail": "Todo not found"}


def test_clear_completed_tasks():
    client.post("/todos/", json={"task": "Done task 1", "completed": True})
    client.post("/todos/", json={"task": "Done task 2", "completed": True})
    client.post(
        "/todos/", json={"task": "Procrastinating task", "completed": False})

    response = client.post("/todos/clear-completed")
    assert response.status_code == 200
    assert response.json() == {"message": "Completed Tasks Deleted"}

    response = client.get("/todos")
    assert response.status_code == 200
    data = response.json()
    assert len(data) == 1
    assert data[0]["task"] == "Procrastinating task"
