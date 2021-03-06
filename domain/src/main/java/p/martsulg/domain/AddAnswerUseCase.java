package p.martsulg.domain;


import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.AnswersParams;
import p.martsulg.data.models.CommentParams;

public class AddAnswerUseCase extends UseCase<AnswersParams,Object> {

    @Override
    protected Observable<Object> buildUseCase(AnswersParams params) {
        return RestService.getInstance().addAnswer(params);
    }
}
